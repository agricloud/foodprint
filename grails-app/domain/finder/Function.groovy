package finder

public enum FunctionGrup {
    SYSTEM,
    BASIC;
}

class Function {

	String name
	String title
	FunctionGrup group
	String url
	String desctiption

    static constraints = {
    }
}
