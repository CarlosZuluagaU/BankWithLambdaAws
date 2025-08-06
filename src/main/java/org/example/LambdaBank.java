package org.example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class LambdaBank implements RequestHandler<BankRequest, BankResponse> {


    @Override
    public BankResponse handleRequest(BankRequest bankRequest, Context context) {

        MathContext mathContext = MathContext.DECIMAL128;

        BigDecimal amount = bankRequest.getAmount().setScale(2, RoundingMode.HALF_UP);


        //Lo que recibe el monto del banco
        BigDecimal montlyRate = bankRequest.getRate().setScale( 2, RoundingMode.HALF_UP).divide(BigDecimal.valueOf(100), mathContext);

        BigDecimal montlyRateWithAccount = bankRequest.getRate().subtract(BigDecimal.valueOf(0.2), mathContext).setScale(2, RoundingMode.HALF_UP).divide(BigDecimal.valueOf(100), mathContext);

        int term = bankRequest.getTerm();

        BigDecimal montlyPayment = this.calculateQuota(amount, montlyRate, term, mathContext);
        BigDecimal montlyPaymentWithAccouunt = this.calculateQuota(amount, montlyRateWithAccount, term, mathContext);

        BankResponse bankResponse = new BankResponse();
        bankResponse.setQuota(montlyPayment);
        bankResponse.setRate(montlyRate);
        bankResponse.setTerm(term);
        bankResponse.setQuotaWithAccount(montlyPaymentWithAccouunt);
        bankResponse.setTermWithAccount(term);
        bankResponse.setRateWithAccount(montlyRateWithAccount);
        //es la misma formula, lo unico que cambia es la tasa de interes, si eres asociado o no
        return bankResponse;

    }

    public BigDecimal calculateQuota(BigDecimal amount, BigDecimal rate, int term, MathContext mathContext) {
        //Paso por paso para calcular la cuota

        //1+i
        BigDecimal onePlusRate = rate.add(BigDecimal.ONE, mathContext);

        //caluclar Cuota
        // P×(1+i)n−1i×(1+i)n
        //(1+i)^n
        BigDecimal OnePlusRateToThePowerOfTerm = onePlusRate.pow(term, mathContext);
        BigDecimal OnePlusRateToThePowerOfTermNegative = BigDecimal.ONE.divide(OnePlusRateToThePowerOfTerm,mathContext);
        //

        //P*i
        BigDecimal numerador= amount.multiply(rate, mathContext);

        //denominator

        BigDecimal denominator = BigDecimal.ONE.subtract(OnePlusRateToThePowerOfTermNegative, mathContext);

        BigDecimal montlyPayment= numerador.divide(denominator, mathContext);

        //La escala representa el número de dígitos a la derecha del punto decimal
        montlyPayment.setScale(2, RoundingMode.HALF_UP);
        return montlyPayment;

    }
}

