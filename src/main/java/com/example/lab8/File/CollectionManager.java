package com.example.lab8.File;



import com.example.lab8.Apps.Edition;
import com.example.lab8.Base.Comparator.ComparatorAge;
import com.example.lab8.Base.Comparator.ComparatorLenght;
import com.example.lab8.Base.Dragon;
import com.example.lab8.Base.DragonType;
import com.example.lab8.DataBase.MainDataBase;
import com.example.lab8.DataBase.Users;
import com.example.lab8.IO.AllAdd;
import com.example.lab8.IO.UserDragon;
import com.example.lab8.MyException.NotIdException;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Класс, выполняющий основые методы для работы с коллекцией
 */
public class CollectionManager {
    VotTvoyId votTvoyId = new VotTvoyId();

    /**
     * Объект класса UserDragon
     */
    UserDragon userDragon = new UserDragon();
    /**
     * Объект класса AllAdd
     */
    AllAdd allAdd = new AllAdd(userDragon);
    /**
     * The Comparator age - по возрасту и весу
     */
    ComparatorAge comparatorAge = new ComparatorAge();

    /**
     * Основная коллекция
     */
    private static final LinkedList<Dragon> baseList = new LinkedList<>();




    public static LinkedList<Dragon> getDragons(){
        return baseList;
    }

    /**
     * Метод, проверяющий коллекцию на пустоту
     *
     * @param dragons the dragons
     * @return the boolean - возвращает boolean
     */
    public boolean isCollectEmpty(LinkedList<Dragon> dragons){
     return dragons.size() == 0;
 }

 public void idget(){
        votTvoyId.votIdBad(baseList);
 }

 public static LinkedList<Dragon> creatorCollection(){
        LinkedList<Dragon> dd = new LinkedList<>();
        for(Dragon dragon : baseList){
          if(Users.getCurrentUser().equals(dragon.getCreator()))  {
              dd.add(dragon);
          }
        }
        return dd;
 }

 public LinkedList<Dragon> retCreator(LinkedList<Dragon> dragons){
     LinkedList<Dragon> dragonss = new LinkedList<>();
     for(Dragon dragon : dragons){
         if(Objects.equals(Users.getCurrentUser(), dragon.getCreator())){
             dragons.add(dragon);
         }
     }
     return dragonss;

 }
    public LinkedList<Long> checkId(LinkedList<Dragon> dragons){
        LinkedList<Long> id = new LinkedList<>();
        int num = dragons.size()+1;
        for (int i = 1; i <= num; i++) {
            id.add((long) i);
        }
        return id;
    }

    /**
     * Метод, который выводит информацию о коллекции
     */
    public String getInfo() {
        StringBuilder stringBuilder = new StringBuilder();
        String first = "Коллекция " + baseList.getClass().getSimpleName();
        String two = "Тип элементов коллекции: " + Dragon.class.getSimpleName();
        String third ="Количество элементов в базе данных : " + baseList.size();
        stringBuilder.append(first);
        stringBuilder.append("\n");
        stringBuilder.append(two);
        stringBuilder.append("\n");
        stringBuilder.append(third);
        return stringBuilder.toString();

        //System.out.println("Количество элементов созданных вами"+retCreator(baseList).size());
         //String formattedDateTime = Users.time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        //System.out.println("Время входа в аккаунт: " + formattedDateTime);
    }


    /**
     * Коллекция, удаляющая дракона с таким же типом, как в аргументе
     *
     * @param type the type - входящая информация от пользователя
     */
    public void removeByType(String type) {
        if(isCollectEmpty(baseList)){
            Edition.showAlert("Ошибка", "Коллекция пуста, команда не имеет смысла", "Пустая коллекция");
            return;
        }
        boolean check = false;
        String c = type.toUpperCase(Locale.ROOT);
        LinkedList<Dragon> listType = new LinkedList<>(baseList);


        for (Dragon dragon : listType) {
            DragonType a = dragon.getType();
            String b = a.toString();
            if (b.equals(c) & Objects.equals(dragon.getCreator(), Users.getCurrentUser())) {
                baseList.remove(dragon);
                System.err.println("Дракон с именем " + dragon.getName() + " удален");
                check = true;
            }
        }
        if(!check){
            String op ="Ни один дракон не удален. Ни у кого нет типа = "+type+ "или же его имеет дракон созданный не вами";
            Edition.showAlert("Ошибка", op, "Не один дракон не удален");
        }
        votTvoyId.votIdBad(baseList);
    }



//готово
    /**
     * Метод, очищающий коллекцию
     */
    public void clear() {
        int size = baseList.size();
        for(int i=0; i< size; i++){
            if(isCollectEmpty(baseList)){
                break;
            }
            Dragon dragon = baseList.get(i);
            if(dragon.getCreator().equals(Users.getCurrentUser())){
                baseList.remove(dragon);
                i--;
                votTvoyId.votIdBad(baseList);
            }
        }
        save();
        System.out.println("Коллекция удалена успешна");
    }


    /**
     * Метод, удаляющий объект по ID, который вводит пользователь
     *
     * @param id the id
     */
    public void removeById(Long id) {
        if(isCollectEmpty(baseList)){
            Edition.showAlert("Ошибка", "Коллекция пуста, команда не имеет смысла", "Пустая коллекция");
            return;
        }
        for (int i = 0; i < baseList.size(); i++) {
            Dragon dragon = baseList.get(i);
            Long check = baseList.get(i).getId();
            if (Objects.equals(check, id)) {
                if(Objects.equals(dragon.getCreator(), Users.getCurrentUser())){
                baseList.remove(i);
                System.out.println("Дракон с id = " + id + " удален успешно");
                System.out.println("Теперь коллекция содержит "+baseList.size()+" элементов");
                votTvoyId.votIdBad(baseList);
                return;
            } else {
                    String op ="Дракон принадлежит другому пользователю ( "+dragon.getCreator()+" ). Вы не можете его удалить";
                    Edition.showAlert("Ошибка", op, "Объект принадлежит другому пользователю ");
                    return;
                }
            }
        }
        Edition.showAlert("Ошибка", "Введенный вами Id не существует", "Нет такого ID");
    }

//готово
    /**
     * Метод, выводящий коллекцию
     */
    public void show() {
        if (baseList.size() == 0) {
            System.err.println("Коллекция пуста");
        } else {
            for (int i = 0; i < baseList.size(); i++) {
                System.out.println("Элемент коллекции №" + (i + 1) + " :" + baseList.get(i).toString());
            }
        }
    }

//готово
    /**
     * Метод, выводящий на экран драконов отсортированных по возрасту
     */
    public String printAscedingAge() {
        StringBuilder stringBuilder = new StringBuilder();
        if(isCollectEmpty(baseList)){
            //System.err.println("Коллекция пуста. Команда бесполезна");
            return "Коллекция пуста. Команда бесполезна";
        }
        LinkedList<Integer> agess = new LinkedList<>();
        LinkedList<Dragon> age = new LinkedList<>(baseList);
        for (Dragon dragon : baseList) {
            agess.add(dragon.getAge());
        }
        agess.sort(Comparator.comparingInt(a -> a));
        for (Integer findAge : agess) {
            for (int b = 0; b < age.size(); b++) {
                Dragon dragon = age.get(b);
                if (Objects.equals(findAge, dragon.getAge())) {
                    String op =" Возрасту " + findAge + " соответствует дракону " + dragon.getName();
                    stringBuilder.append(op);
                    stringBuilder.append("\n");

                    age.remove(dragon);
                }
            }
        }
        return stringBuilder.toString();
    }

//готово
    /**
     * Метод, выводящий на экран драконов отсортированных по типу
     */
    public String printDescendingType() {
        StringBuilder stringbuilder = new StringBuilder();
        if(isCollectEmpty(baseList)){
            //System.err.println("Коллекция пуста. Команда бесполезна");
            return "Коллекция пуста. Команда бесполезна";
        }
        ComparatorLenght comparatorLenght = new ComparatorLenght();
        LinkedList<DragonType> dragonTypes = new LinkedList<>();
        Set<DragonType> uniqueTypes = new HashSet<>();
        for (Dragon dragon : baseList) {
            DragonType type = dragon.getType();
            if (uniqueTypes.add(type)) { // Проверяем, добавлен ли элемент в Set
                dragonTypes.add(type);
            }
        }
        dragonTypes.sort(comparatorLenght.reversed());
        for (DragonType findType : dragonTypes) {
            String findTypeS = findType.toString();
            for (Dragon dragon : baseList) {
                if (findTypeS.equals(dragon.getType().toString())) {
                   String op =" Типу " + findTypeS + " соответствует дракону " + dragon.getName();
                    stringbuilder.append(op);
                    stringbuilder.append("\n");
                }
            }
        }
        return stringbuilder.toString();
    }

    /**
     * Метод добавляющий драконов в коллекцию
     * @see AllAdd#groupMethod()
     */
    public void add(Dragon change) {
        //Dragon change = allAdd.groupMethod();
        baseList.add(change);
        System.out.println("Дракон добавлен");
        System.out.println("Теперь коллекция содержит "+baseList.size()+" элементов");
        votTvoyId.votIdBad(baseList);
    }

//готово
    /**
     * Метод, обновляющий дракона по id
     * @see AllAdd#groupMethod()
     * @param id the id
     */
    public void updateId(long id) {
        if(isCollectEmpty(baseList)){
            System.err.println("Коллекция пуста. Команда бесполезна");
            Edition.showAlert("Ошибка", "Коллекция пуста. Команда бесполезна", "Пустая коллекция");

            return;
        }
        if (!Objects.equals(Users.getCurrentUser(), baseList.get((int)id - 1).getCreator())){
            System.out.println("Дракон принадлежит другому пользователю. Вы не можете его удалить");
            Edition.showAlert("Ошибка", "Дракон принадлежит другому пользователю. Вы не можете его удалить", "Объект принадлежит другому пользователю ");
            return;
        }
        LinkedList<Long> idd =checkId(baseList);
        try {
            if (!idd.contains(id)) throw new NotIdException();
        } catch (NotIdException e) {
            System.err.println("Дракона с таким id не существует");
            Edition.showAlert("Ошибка", "Дракона с таким id не существует", "Ошибка с id ");

            return;
        }
        System.out.println("Введите данные драконы, на которого хотите заменить дракона с id = " + id);
        Dragon change = allAdd.groupMethod();
        //System.out.println(change.toString());
        for (int i = 0; i < baseList.size(); i++) {
            Dragon dragon = baseList.get(i);
            if (baseList.get(i).getId().equals(id) & Objects.equals(dragon.getCreator(), Users.getCurrentUser())) {
                baseList.set(i, change);
                String op ="Дракон с ID " + id + " был заменен на дракона с именем "+change.getName();
                Edition.showAlertHelp("Успешно", op, "Объект заменен ");
                votTvoyId.votIdBad(baseList);
                return;
            }
        }
        System.err.println("Дракон с ID " + id + " не найден в коллекции");
        Edition.showAlert("Ошибка", "Дракона с таким id не существует", "Ошибка с id ");
    }
//готово
    /**
     * метод, который добавяет новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции
     * @see AllAdd#groupMethod()
     * @see ComparatorAge#compare(Dragon, Dragon)
     */
    public void addIfMin(Dragon dragon) {
        if(isCollectEmpty(baseList)){
            System.err.println("Коллекция пуста. Команда бесполезна");
            return;
        }

        baseList.sort(comparatorAge);
        Dragon champion = baseList.get(0);
        int result = comparatorAge.compare(champion, dragon);
        if (!(result < 0)) {
            baseList.add(dragon);
            votTvoyId.votIdBad(baseList);
        } else {
            System.out.println("У наименьшего элемента коллекции значение меньше");
            Edition.showAlert("Ошибка", "У наименьшего элемента коллекции значение меньше", "Add_if_min");
        }
        System.out.println("Теперь коллекция содержит "+baseList.size()+" элементов");
    }

    /**
     * Метод, сохраняющий файл в коллекцию
     * Путь к файлу задается в аргументе

     * @see OutputStreamWriter#write(String)


     */
    //проверить повтоящиеся драконы
    public void save() {
        MainDataBase.requestSQLWithout("DELETE FROM DRAGONS;");
        MainDataBase.requestSQLWithout("ALTER SEQUENCE DRAGONSEQ RESTART WITH 1;");
        for (Dragon dragon : baseList) {
            //System.out.println("Vot smotri "+dragon.getCreationDate()+"kkk");
            MainDataBase.requestSQLWithout("insert into dragons (id, creator, creationDate, name, age, color, type, weight, size, eyesCount, toothCount, x, y) values (nextval('DRAGONSEQ') , '" + dragon.getCreator() + "', '" + dragon.getCreationDate().toString() + "', '" + dragon.getName() + "', '" + dragon.getAge() + "', '" + dragon.getColor() + "', '" + dragon.getType() + "', '" + dragon.getWeight() + "', '"+dragon.getHead().getSize()+ "', '"+ dragon.getHead().getEyesCount() + "', '"+dragon.getHead().getToothCount()+"', '" + dragon.getCoordinates().getX() + "', '" + dragon.getCoordinates().getY() + "')");
        }
        System.out.println("Коллекция сохранена в базе данных");
    }
//готово
    /**
     * Метод, удаляющий дракона по index. Он задается в аргумент
     * @param i the
     */
    public void removeIndex(int i) {
        if(isCollectEmpty(baseList)){
            System.err.println("Коллекция пуста. Команда бесполезна");
            return;
        }
        if (!Objects.equals(Users.getCurrentUser(), baseList.get(i).getCreator())){
            System.out.println("Дракон принадлежит другому пользователю. Вы не можете его удалить");
            return;
        }
        try {
            if (i <= baseList.size() & Objects.equals(baseList.get(i).getCreator(), Users.getCurrentUser())) {
                baseList.remove(i);
                votTvoyId.votIdBad(baseList);
                System.out.println("Дракон удален");
            } else {
                System.err.println("Нет такого индекса в коллекции");
            }
            System.out.println("Теперь коллекция содержит "+baseList.size()+" элементов");
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Нет такого индекса в коллекции");
        }
    }

    /**
     * Метод, удаляющий из коллекции все элементы, превышающие заданный
     * Вы задаете команде ID дракона по которому хотите сравнивать, если хотите сравнивать по новому введите любое число(которое не является ID)
     * @see AllAdd#groupMethod()
     * @param id the id
     */
    public void removeGreater(Long id) {
        boolean checkGreader = false;

        if(isCollectEmpty(baseList)){
            System.err.println("Коллекция пуста. Команда бесполезна");
            return;
        }
        LinkedList<Dragon> greader = new LinkedList<>();
        for(Dragon dragon : baseList){
            if(Objects.equals(id, dragon.getId())){
            greader.add(dragon);
            }
        }
        if(greader.size()==0) {
            Dragon heRemove = allAdd.groupMethod();
            greader.add(heRemove);
        }
        baseList.sort(Collections.reverseOrder(comparatorAge));
        for (int i = 0; i < baseList.size(); i++) {
            Dragon dragon = baseList.get(i);
            int result = comparatorAge.compare(greader.getFirst(), dragon);
            if (result < 0 & Objects.equals(Users.getCurrentUser(), dragon.getCreator())) {
                baseList.remove(dragon);

                System.out.println(dragon.getName() + " удален");
                System.out.println("Теперь коллекция содержит "+baseList.size()+" элементов");
                checkGreader = true;
            }
        }
        votTvoyId.votIdBad(baseList);
        if(!checkGreader){
            System.err.println("Ни один дракон не удален");
        }
    }
    /**
     * Метод, удаляющий из коллекции все элементы, превышающие заданный
     */
    public void gread(double d){
        boolean fact = false;
        LinkedList<Integer> index = new LinkedList<>();
        for(int i =0; i < baseList.size(); i++){
            Dragon dragon = baseList.get(i);
            if(d<dragon.getHead().getSize()){
               index.add(i);
               fact = true;
            }
        }
        Iterator<Integer> iter = index.descendingIterator();
        while (iter.hasNext()) {
            int integer = iter.next();
            if(Objects.equals(baseList.get(integer).getCreator(), Users.getCurrentUser())) {
                baseList.remove(integer);
            }
        }
        if (fact) {
            System.out.println("Драконы удалены");
            votTvoyId.votIdBad(baseList);
        } else {
            System.out.println("Ни один дракон не удален");
            Edition.showAlertHelp("Уведомление", "Ни один дракон не удален", "Никто не удален");
        }
        System.out.println("Теперь коллекция содержит "+baseList.size()+" элементов");
    }

    public boolean checkCreator(Dragon dragon){
        boolean eee = true;
        String nameCreator = dragon.getCreator();
        if(!Users.getCurrentUser().equals(nameCreator)){
            System.out.println("Дракона, создавал не ты");
            eee = false;
        }
        return eee;
    }
public void logOut(){
Users.setCurrentUser(null);
    System.out.println("Вы успешно вышли из аккаунта");
}
    public void clearData(){
        MainDataBase.requestSQLWithout("DELETE FROM DRAGONS;");
        MainDataBase.requestSQLWithout("DELETE FROM USERS;");
    }

}





