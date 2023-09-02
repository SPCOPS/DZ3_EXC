import java.io.*;
import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class UserDataProcessor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите данные в формате: Фамилия Имя Отчество датарождения номертелефона пол");

        try {
            String input = scanner.nextLine();
            String[] inputData = input.split(" ");

            if (inputData.length != 6) {
                throw new IllegalArgumentException("Неверное количество данных. Введите все данные.");
            }

            String lastName = inputData[0];
            String firstName = inputData[1];
            String middleName = inputData[2];
            String birthDateStr = inputData[3];
            String phoneNumberStr = inputData[4];
            String genderStr = inputData[5];

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            Date birthDate = dateFormat.parse(birthDateStr);

            long phoneNumber = Long.parseLong(phoneNumberStr);

            if (!genderStr.equals("f") && !genderStr.equals("m")) {
                throw new IllegalArgumentException("Неверное значение пола. Используйте 'f' или 'm'.");
            }

            String outputFileName = lastName + ".txt";
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName, true));
            writer.write(lastName + " " + firstName + " " + middleName + " " +  dateFormat.format(birthDate) + " " + phoneNumber + " " + genderStr);
            writer.newLine();
            writer.close();

            System.out.println("Данные успешно записаны в файл " + outputFileName);
        } catch (ParseException e) {
            System.err.println("Ошибка при разборе даты рождения. Используйте формат dd.MM.yyyy.");
        } catch (NumberFormatException e) {
            System.err.println("Ошибка при разборе номера телефона. Введите целое число без форматирования.");
        } catch (IllegalArgumentException e) {
            System.err.println("Ошибка: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Ошибка при записи данных в файл: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
