# Proyecto TDD - Registro de Votantes para una Registraduría Santiago Pulido

## Descripción
Proyecto de ejemplo para aplicar TDD (Red → Green → Refactor) y probar reglas de negocio (dominio) aisladas. 
Se implementa el caso de uso *registerVoter(Person)* con clases de equivalencia y valores límite.

## Estructura del proyecto
- `src/main/java/edu/unisabana/dyas/tdd/registry/` - Clases del dominio (Person, Gender, RegisterResult, Registry).
- `src/test/java/edu/unisabana/dyas/tdd/registry/` - Pruebas unitarias (RegistryTest y tests complementarios).
- `pom.xml` - Configuración Maven (JUnit, JaCoCo).

## Requisitos
- Java 8
- Maven 3.x

## Ejecutar compilación y pruebas
Desde la carpeta `ClasesEquivalencia` (donde está el `pom.xml`):

```bash
mvn clean test
mvn jacoco:report
```

El reporte de cobertura se genera en `target/site/jacoco/index.html`.

## Reglas de negocio implementadas
- Si la persona es `null` → `INVALID`.
- Si el id <= 0 → `INVALID`.
- Si la persona no está viva (`alive = false`) → `DEAD`.
- Si la edad < 0 o > 120 → `INVALID_AGE`.
- Si la edad < 18 → `UNDERAGE`.
- Si el id ya fue registrado → `DUPLICATED`.
- Caso contrario → `VALID`.

## Clases de equivalencia (matriz)
| Caso | Entrada (id, edad, alive) | Resultado esperado | Test |
|---|---:|---|---|
| Persona null | null | INVALID | shouldReturnInvalidWhenPersonIsNull |
| Id inválido | id=0, edad=25, alive=true | INVALID | shouldRejectWhenIdIsZeroOrNegative |
| Menor de edad | id=10, edad=17, alive=true | UNDERAGE | shouldRejectUnderageAt17 |
| Adulto mínimo | id=11, edad=18, alive=true | VALID | shouldAcceptAdultAt18 |
| Adulto máximo | id=12, edad=120, alive=true | VALID | shouldAcceptMaxAge120 |
| Edad inválida alta | id=13, edad=121, alive=true | INVALID_AGE | shouldRejectInvalidAgeOver120 |
| Persona muerta | id=14, edad=40, alive=false | DEAD | shouldRejectDeadPerson |
| Duplicado | id=777, edad=30, alive=true (dos inscripciones) | DUPLICATED | shouldReturnDuplicatedWhenSameIdRegistered |

## Evidencia de TDD
Se han creado pruebas unitarias que cubren las clases de equivalencia anteriormente listadas siguiendo el patrón AAA (Arrange–Act–Assert).

### Historia TDD (ejemplo)
- test: add shouldReturnInvalidWhenPersonIsNull (RED)  
- feat: implement null check and return INVALID (GREEN)  
- refactor: extract constants MIN_AGE, MAX_AGE (REFACTOR)  

## Defectos
Se incluye el archivo `defectos.md` para registrar fallos detectados por las pruebas.

