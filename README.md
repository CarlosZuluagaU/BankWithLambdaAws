Calculadora de Cuotas de Crédito - AWS Lambda
Este proyecto es una función AWS Lambda desarrollada en Java que sirve como un microservicio para calcular la cuota mensual de un préstamo bancario. La lógica implementa el sistema de amortización francés, asegurando pagos fijos durante toda la vida del crédito.

El proyecto está diseñado para ser robusto, utilizando BigDecimal para todos los cálculos monetarios y de tasas, garantizando así la máxima precisión.

✨ Características Principales
Cálculo de Cuota Estándar: Calcula la cuota mensual basándose en monto, tasa de interés y plazo.

Cálculo de Cuota con Descuento: Ofrece un cálculo alternativo para clientes con una tasa de interés preferencial (por ejemplo, clientes asociados al banco).

Precisión Financiera: Usa la clase java.math.BigDecimal para evitar errores de punto flotante en los cálculos.

Listo para la Nube: Empaquetado como una función serverless, lista para ser desplegada en AWS Lambda.

⚙️ Lógica del Cálculo
La cuota mensual se calcula utilizando la fórmula estándar de la anualidad:

Cuota=
1−(1+i)
−n

P×i
​

Donde:

P: Monto del préstamo (Principal).

i: Tasa de interés mensual (en formato decimal, ej: 1.5% = 0.015).

n: Número total de cuotas (plazo en meses).

🚀 Cómo Usarlo (API)
Una vez desplegada la función en AWS Lambda, se puede invocar a través de su endpoint de API Gateway o directamente con el SDK de AWS.

Petición (Request)
El cuerpo de la petición (BankRequest) debe ser un JSON con la siguiente estructura:

JSON

{
"amount": 20000000,
"rate": 2.1,
"term": 48
}
amount (numérico): El monto total del préstamo que se solicita.

rate (numérico): La tasa de interés mensual expresada como porcentaje (ej: 2.1 para 2.1%).

term (entero): El plazo total del crédito en número de meses.

Respuesta (Response)
La función devolverá (BankResponse) un JSON con el resultado de ambos cálculos:

JSON

{
"quota": 619545.98,
"rate": 0.021,
"term": 48,
"quotaWithAccount": 601334.45,
"rateWithAccount": 0.019,
"termWithAccount": 48
}
quota: El valor de la cuota mensual con la tasa de interés estándar.

rate: La tasa de interés estándar convertida a formato decimal.

term: El plazo del crédito.

quotaWithAccount: El valor de la cuota mensual con la tasa de interés preferencial (descuento de 0.2).

rateWithAccount: La tasa de interés preferencial convertida a formato decimal.

termWithAccount: El plazo del crédito (es el mismo para ambos cálculos).

🛠️ Tecnologías Utilizadas
Lenguaje: Java 11 (o superior)

Frameworks/Librerías:

aws-lambda-java-core: Para la integración con AWS Lambda.

Plataforma: AWS Lambda

Build Tool: Maven / Gradle


Carlos Andrés Zuluaga 
Git hub:  https://github.com/CarlosZuluagaU
Linkedin: 