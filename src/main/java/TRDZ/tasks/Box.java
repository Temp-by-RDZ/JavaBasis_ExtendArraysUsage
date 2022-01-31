package TRDZ.tasks;

import java.util.ArrayList;

public class Box<TFruit extends Fruit> {

	private final ArrayList<TFruit> Contain = new ArrayList<>();	//Список фруктов
	private final Class<TFruit> Mark;								//Полученный класс
	private final int id;											//Ид для защищенного списка

	public Box(Class<TFruit> Mark,int id){
		this.Mark=Mark;
		this.id=id;
		}

	/**
	 * Работа с классом
	 * @param ends окончание слова
	 * @return тип фрукта с окончанием
	 */
	public String MyMark(String ends) {
		if (Mark==Fruit_Apple.class) return ("яблоко"+ends);
		else if (ends.equals("м")) return ("апельсино"+ends);
		else return "апельсин";
		}

	/**
	 * Добавление фрукта в ящик
	 * @param fruit фрукт
	 */
	public void add(TFruit fruit) {
		Contain.add(fruit);
		System.out.println("Вы положили в ящик "+MyMark(""));
		System.out.println("Его класс: "+fruit.getClass());
		}

	/**
	 * Добавление множества фруктов в ящик
	 * @param other_cont фрукт
	 */
	public void add_all(ArrayList<TFruit> other_cont) {
		Contain.addAll(other_cont);
		}

	/**
	 * Удаление фрукта из ящика
	 */
	public void get() {
		if (!Contain.isEmpty()) {
			Contain.remove(0);
			System.out.println("Вы забрали "+MyMark(""));
			}
		else System.out.println("В коробке ничего нет");
		}

	/**
	 * Удаление всех фруктов из ящика
	 */
	public void drop_out() {
		Contain.clear();
		System.out.println("Вы вытрехнули коробу. Если в ней что-то и было то сейчас она точно пуста.");
		}

	/**
	 * Передача всех фруктов в другой ящик
	 * @param other другой ящик
	 */
	public void drop_to(Box<TFruit> other) {
		other.add_all(Contain);
		Contain.clear();
		}

	/**
	 * Получить индекс защищенного списка
	 * @return индекс
	 */
	public int get_Id() {return id;}

	/**
	 * Получение веса содержиого ящика
	 * @return вес содержимого в фунтах
	 */
	public double get_Weight() {
		if (!Contain.isEmpty()) return (Contain.size()*Contain.get(0).get_Weight());
		else return 0;
		}

	/**
	 * Сравнение веса содержимого ящика с весом содержимого другого ящика
	 * @param other другой ящик
	 * @return true / false
	 */
	public boolean compare(Box<? extends Fruit> other) {
		return this.get_Weight() == other.get_Weight();
		}

	@Override
	public String toString() {
		return ("На этом ящике красуется картинка с "+MyMark("м")+". В нем хранится фруктов:"+Contain.size()+".");
		}

	}
