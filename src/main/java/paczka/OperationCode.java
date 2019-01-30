package paczka;

public enum OperationCode {

    //Operacje wejscia - wyjscia 10
    READ("A"),
    WRITE("B"),

    //Operacje zaladowania i zapamietania 20
    LOAD("14"),
    STORE("15"),

    //Operacje arytmetyczne 30
    ADD("1E"),
    SUBSTRUCT("1F"),
    DIVIDE("20"),
    MULTIPLY("21"),
    DIVISION_WITH_REMAINDER("22"),
    POW("23"),

    //Operacje sterujace 40
    BRANCH("28"),
    BRANCH_NEG("29"),
    BRANCH_ZERO("2A"),
    HALT("2B"),

    //Operacje tekstowe 50
    READ_SENTENCES("32"),
    WRITE_SENTENCES("33");

    private String hexValue;

    OperationCode(String hexValue) {
        this.hexValue = hexValue;
    }

    public String getHexValue() {
        return hexValue;
    }

    public static OperationCode operationCode(String operationValue) {
        for(OperationCode operationCode : OperationCode.values()) {
            if(operationCode.getHexValue().equals(operationValue)) {
                return operationCode;
            }
        }
        return null;
    }
}
