package main_service;

public class App 
{
    public static void main( String[] args )
    {
    	System.out.println("Выбранная предметная область - видеохостинг." +
    			"\n Наша упрощенная бизнес-модель построена на взаимодействии пользователей и рекламодателей." +
    			"\n Платформа зарабатывает на комиссии за переводы денег за рекламу (45%, цифра взята с расценок YouTube).");

        AppService app = new AppService();

    	app.createPlatform(0.45);
    	app.createUser("David");
    	app.createUser("Max");
    	app.createUser("John");
    	
    	app.printUsers();
    }
}