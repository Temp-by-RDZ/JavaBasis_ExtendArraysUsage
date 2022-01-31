package TRDZ.tasks;

import java.util.*;

public class Initialization {

	protected static final Scanner scanner = new Scanner(System.in);
	private static final String[] accept = {"Yes","yes","1","true","True","Да","да","+"}; 	//Задаем перечень согласия
	protected static ArrayList<Box<? extends Fruit>> Boxes = new ArrayList<>();       		//Создаем склад для всех ящиков
	protected static ArrayList<Box<Fruit_Apple>> Strong_ABox = new ArrayList<>();       	//Создаем защещеный список ящиков с яблоками
	protected static ArrayList<Box<Fruit_Orange>> Strong_OBox = new ArrayList<>();       	//Создаем защещеный список ящиков с апельсинами

	public static void main(String[] args) {
	//region 1. Обработка массивов через обобщенные модули
		Integer[] arr = {1,2,3};
		LinkedList<Integer> rra =new LinkedList<>(Arrays.asList(arr));
		System.out.print("\nФиксированый Массив: "+Arrays.toString(arr));
		System.out.print("\nКоллекция:           "+rra);
		System.out.println("\nЗамена местами 1ого и 3его элемента");
		Switch(arr,2,0);
		Switch(rra,2,0);
		System.out.print("Фиксированый Массив: "+Arrays.toString(arr));
		System.out.print("\nКоллекция:           "+rra);
		System.out.println("\nПреобразование массива в ArrayList");
		rra.addAll(ConvertTo_ArrayList(arr)); //Трансформация и обьединение для наглядности
		System.out.print("Обьединенные Коллекции: "+rra+"\n\n");
	//endregion
	//region 2. Корзинки и фрукты
		Box_game();
	//endregion
	//region 3. Наглядная демонстрация работы сегмента с обобщениями
		Box<Fruit_Apple> Box_apple1 = new Box<>(Fruit_Apple.class,0);
		Box<Fruit_Apple> Box_apple2 = new Box<>(Fruit_Apple.class,0);
		Box<Fruit_Orange> Box_orange1 = new Box<>(Fruit_Orange.class,0);
		Box<Fruit_Orange> Box_orange2 = new Box<>(Fruit_Orange.class,0);

		Box_apple1.add(new Fruit_Apple());
		//Box_apple2.add(new Fruit_Orange()); ERROR
		Box_orange1.add(new Fruit_Orange());
		//Box_orange2.add(new Fruit_Apple()); ERROR
		System.out.println("\nЯ1-"+Box_apple1);
		System.out.println("Я2-"+Box_apple2);
		System.out.println("О1-"+Box_orange1);
		System.out.println("О2-"+Box_orange2);

		Box_apple1.drop_to(Box_apple2);
		//Box_apple1.drop_to(Box_orange2); ERROR
		Box_orange1.drop_to(Box_orange2);
		//Box_orange1.drop_to(Box_apple2); ERROR
		System.out.println("\nЯ1-"+Box_apple1);
		System.out.println("Я2-"+Box_apple2);
		System.out.println("О1-"+Box_orange1);
		System.out.println("О2-"+Box_orange2);
	//endregion
		}

	/**
	 * Замена двух элементов масива местами
	 * @param List обрабатываемый масив
	 * @param from индекс первого элемента
	 * @param to индекс второго элемента
	 */
	public static<TArray> void Switch(TArray[] List, int from, int to) {
 		TArray getter;
		getter=List[to];
		List[to]=List[from];
		List[from]=getter;
		}

	/**
	 * Замена двух элементов масива местами
	 * @param List обрабатываемый масив
	 * @param from индекс первого элемента
	 * @param to индекс второго элемента
	 */
	public static<TArray> void Switch(AbstractList<TArray> List, int from, int to) {
		if (from>to) { //Защита последовательности от искажений
			TArray getter=List.get(from);
			List.remove(from);
			List.add(from,List.get(to));
			List.remove(to);
			List.add(to,getter);
			}
		else {
			TArray getter=List.get(to);
			List.remove(to);
			List.add(to,List.get(from));
			List.remove(from);
			List.add(from,getter);
			}
		}

	/**
	 * Трансформация массива в тип лист
	 * @param List Обрабатываемый мысив
	 * @return Трансформированый массив
	 */
	public static<TArray> ArrayList<TArray> ConvertTo_ArrayList (TArray[] List) {
		return new ArrayList<>(Arrays.asList(List));
		}

	/**
	 * Обработка и взаимодействие с корзинками и фруктами
	 */
	public static void Box_game(){
		while (true) {
			int action = 0;
			Faze_home();
		//region Ввод действия
			while (!(action >0)) {
				action=Get_action();
				if (action >3) {        	//Оформление ошибки ввода
					Faze_home();
					action =0;}
				scanner.nextLine();
				}
		//endregion
		//region Взаимодействия
			switch (action) {
			case 1:	//Создание нового ящика
			//region Выбор маркеровки ящика
				action = 0;
				Faze_create();
				while (!(action >0)) {
					action=Get_action();
					if (action > 2) {        //Оформление ошибки ввода
						Faze_create();
						action =0;}
					scanner.nextLine();
					}
			//endregion
			//region Добавление ящика
				if (action==1) { //Обрабатываем ящик
					Box<Fruit_Apple> Temp_Box = new Box<>(Fruit_Apple.class,(Strong_ABox.size())); //Создаем ящик яблок
					Boxes.add(Temp_Box);					 //Добавляем ящик на склад
					Strong_ABox.add(Temp_Box);
					}
				else {
					Box<Fruit_Orange> Temp_Box = new Box<>(Fruit_Orange.class,(Strong_OBox.size())); //Создаем ящик апельсинов
					Boxes.add(Temp_Box);					 //Добавляем ящик на склад
					Strong_OBox.add(Temp_Box);
					}
			//endregion
				break;
			case 2:	//Взаимодействие с ящиком(добавить, забрать, переложить, взвесить, сравнить, выбростить,
				if (!Boxes.isEmpty()) Storage();
				else System.out.println("Склад пуст. У вас пока нет ящиков.");
				break;
			case 3:	//EXIT
				System.out.println("Вы действительно хотите уйти?");
				if (Arrays.asList(accept).contains(scanner.next())) {return;}
				}
		//endregion
			}
		}

	/**
	 * Обработка взаимодействия с ящиком
	 */
	private static void Storage() {
	//region Выбор ящика
		int tek =0;
		while (!(tek >0)) {
			System.out.println("\nУ вас ящиков: "+Boxes.size()+".\nКакой вы хотите осмотреть?");
			tek=Get_action();
			if (tek > Boxes.size()) {        //Оформление ошибки ввода
				System.out.println("Нет такого ящика.");
				tek = 0;}
			scanner.nextLine();
			}
		tek--;
	//endregion
		while (true) {
		//region Выбор Действия с ящиком
			Faze_storage(tek);
			int action =0;
			while (!(action >0)) {
				action=Get_action();
				if (action >7) {        //Оформление ошибки ввода
					Faze_storage(tek);
					action = 0;}
				scanner.nextLine();
				}
		//endregion
		//region Выполнение выбранного действия
			switch (action) {
			case 1: //Добавить фрукт
				if (Boxes.get(tek).MyMark("").equals("яблоко")) { //Компилятор не даст нам добавить не тот фрукт потому для каждой защиты - свой фрукт
					Strong_ABox.get(Boxes.get(tek).get_Id()).add(new Fruit_Apple()); //Используя защищенный массив добавляем фрукт
					//В данном списке указан тип и при обращениии через него некоректный ввод запрещен.
					}
				else {
					Strong_OBox.get(Boxes.get(tek).get_Id()).add(new Fruit_Orange()); //Используя защищенный массив добавляем фрукт
					//В данном списке указан тип и при обращениии через него некоректный ввод запрещен.
					}
				break;
			case 2: //Забрать фрукт
				//Для удаления тип не нужен
				Boxes.get(tek).get();
				break;
			case 3: //Переложить фрукты
				if (Boxes.size()<2) System.out.println("У вас нет других коробок.");
				else {
				//region Выбор ящика
					int nxt =0;
					while (!(nxt >0)) {
						System.out.println("\nУ вас ящиков: "+Boxes.size()+".\nВ какой ящик вы хотите переложить фрукты("+Boxes.get(tek).MyMark("")+"), из ящика "+(tek+1)+"?");
						nxt=Get_action();
						if (nxt > Boxes.size()) {        //Оформление ошибки ввода
							System.out.println("Нет такого ящика.");
							nxt = 0;}
						scanner.nextLine();
						}
					nxt--;
				//endregion
				//region Перемещение фруктов между коробками
					if (tek==nxt) System.out.println("Это тот же самый ящик");
					else {
						if (Boxes.get(tek).MyMark("").equals(Boxes.get(nxt).MyMark("")))
							{//Реализовать можно было проще но для сохранения задачи пришлось вновь прибегнуть к защищенному списку
							if (Boxes.get(tek).MyMark("").equals("яблоко")) {
								Strong_ABox.get(Boxes.get(tek).get_Id()).drop_to(Strong_ABox.get(Boxes.get(nxt).get_Id()));
								//Используя защищенный массив добавляем фрукт
								//В данном списке указан тип и при обращениии через него некоректный ввод запрещен.
								}
							else {
								Strong_OBox.get(Boxes.get(tek).get_Id()).drop_to(Strong_OBox.get(Boxes.get(nxt).get_Id()));
								//Используя защищенный массив добавляем фрукт
								//В данном списке указан тип и при обращениии через него некоректный ввод запрещен.
								}
							}
						else System.out.println("Нельзя смешывать фрукты. В коробку с яблоками только яблоки и наоборот.");
						}
				//endregion
					}
				break;
			case 4: //Взвесить коробку
				System.out.println("Вес содержимого коробки: "+Boxes.get(tek).get_Weight());
				break;
			case 5: //Сравнить коробки
				if (Boxes.size()<2) System.out.println("У вас нет других коробок.");
				else {
				//region Выбор ящика
					int nxt =0;
					while (!(nxt >0)) {
						System.out.println("\nУ вас ящиков: "+Boxes.size()+".\nС каким вы хотите сравнить, ящик "+(tek+1)+"?");
						nxt=Get_action();
						if (nxt > Boxes.size()) {        //Оформление ошибки ввода
							System.out.println("Нет такого ящика.");
							nxt = 0;}
						scanner.nextLine();
						}
					nxt--;
				//endregion
				//region Сравнение ящиков
					if (tek==nxt) System.out.println("Это тот же самый ящик");
					else {
						if (Boxes.get(tek).compare(Boxes.get(nxt))) System.out.println("Эти коробки весят одинаково по "+Boxes.get(tek).get_Weight()+"f");
						else System.out.println("Коробки имеют разный вес. "+Boxes.get(tek).get_Weight()+"f и "+Boxes.get(nxt).get_Weight()+"f");
						}
				//endregion
					}
				break;
			case 6: //Выбросить все из короби
				Boxes.get(tek).drop_out();
				break;
			case 7:	//EXIT
				return;
				}
		//endregion
			}
		}

	/**
	 * Получение номера действия
	 * @return результат действия
	 */
	private  static int Get_action() {
		int action;
		if (scanner.hasNextInt()) { action = scanner.nextInt(); } //Проверка на некоректный ввод
		else { action = 69; }
		return action;
		}

	/**
	 * Вывод текста(сценки) в доме
	 */
	private static void Faze_home() {
		System.out.println("\nВы на ферме у вас есть фрукты и склад ящиков на котором: "+Boxes.size());
		System.out.println("1 - Добавить ящик");
		System.out.println("2 - Осмотреть ящик");
		System.out.println("3 - Уйти");
		}

	/**
	 * Вывод текста(сценки) на складе
	 */
	private static void Faze_storage(int tek) {
		System.out.println("\n"+Boxes.get(tek));
		System.out.println("1 - Добавить "+Boxes.get(tek).MyMark(""));
		System.out.println("2 - Забрать "+Boxes.get(tek).MyMark(""));
		System.out.println("3 - Переложить фрукты");
		System.out.println("4 - Взвесить");
		System.out.println("5 - Сравнить ящики");
		System.out.println("6 - Выбросить содержимое");
		System.out.println("7 - Уйти");
		}

	/**
	 * Вывод текста(сценки) добавления ящика
	 */
	private static void Faze_create() {
		System.out.println("\nВы сделали новый ящик, как его пометить?");
		System.out.println("1 - Ящик для яблок");
		System.out.println("2 - Ящик для апельсинов");
		}

	}
