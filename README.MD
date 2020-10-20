
Usage examples :


        OldPoundCalculator oldPoundCalculator = new OldPoundCalculator();
		
        String addResult = oldPoundCalculator.add("5p 17s 8d", "3p 4s 10d");
        String subtractResult = oldPoundCalculator.subtract("5p 17s 8d", "3p 4s 10d");
        String multiplyResult = oldPoundCalculator.multiply("5p 17s 8d", 2); 
        String divideResult = oldPoundCalculator.divide("5p 17s 8d", 3);
		
        System.out.println("add result: " + addResult);
        System.out.println("subtract result: " + subtractResult);
        System.out.println("multiply result: " + multiplyResult);
        System.out.println("divide result: " + divideResult);