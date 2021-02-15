package test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class TestStudentRegistrationForm {
    @BeforeAll
    static void setup() {
        Configuration.startMaximized = true;
    }

    @Test
    void dataAppearsInOutputBlockTest() {

         String firstName = "Yulia";
         String lastName = "K";
         String userEmail = "test@test123.com";
         String mobileNumber = "1234567890";
         String currentAddress =  "some current address";
         String subjects = "Computer Science";
         String gender = "Other";
         String dayOfBirth = "28";
         String monthOfBirth = "January";
         String yearOfBirth = "1990";
         String hobbies = "Music";
         String state = "Rajasthan";
         String city = "Jaipur";
         String pictureName = "/Screenshot_8.png";

        // arrange

        open("https://demoqa.com/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));

        // actions

        $("#firstName").setValue(firstName); //Указание значения FirstName
        $("#lastName").setValue(lastName); //Указание значения LastName
        $("#userEmail").setValue(userEmail); //Указание значения Email
        $(byText(gender)).click(); //Хотелось бы получить комментарии, как искать элемент по типу (радиобатон, чекбокс, и т.д.)
        $("#userNumber").setValue(mobileNumber); //Указание Mobile

        $("#dateOfBirthInput").click(); // Выбор месяца и года рождения в календаре
        $(".react-datepicker__month-select").click();
        $$(".react-datepicker__month-select option").find(text(monthOfBirth)).click();
        $(".react-datepicker__year-select").click();
        $$(".react-datepicker__year-select option").find(value(yearOfBirth)).click();
        $(".react-datepicker__month").click();
        $$(".react-datepicker__day").find(exactText(dayOfBirth)).click(); // Выбор дня в календаре

        $("#subjectsContainer").click(); //Указание Subject
        $("#subjectsContainer input").val(subjects).pressEnter();

        $("#uploadPicture").uploadFile(new File(".\\src\\test\\java\\test"+ pictureName)); //Указание Picture

        $("#currentAddress").setValue(currentAddress); //Указание Current Address

        $("#state").click(); //Указание State and City
        $(byText(state)).click();
        $("#city").click();
        $(byText(city)).click();

        $("#submit").click();


        // assert

        ElementsCollection elements = $$(".table-responsive tr");
        elements.filterBy(text("Student Name")).shouldHave(texts(firstName + " " + lastName));
        elements.filterBy(text("Student Email")).shouldHave(texts(userEmail));
        elements.filterBy(text("Gender")).shouldHave(texts(gender));
        elements.filterBy(text("Mobile")).shouldHave(texts(mobileNumber));
        elements.filterBy(text("Date of Birth")).shouldHave(texts(dayOfBirth + " " + monthOfBirth + "," + yearOfBirth));
        elements.filterBy(text("Student Email")).shouldHave(texts(userEmail));
        elements.filterBy(text("Subjects")).shouldHave(texts(subjects));
        elements.filterBy(text("Address")).shouldHave(texts(currentAddress));
        elements.filterBy(text("State and City")).shouldHave(texts(state + " " + city));
    }
}
