package ru.stqa.javacourse.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.javacourse.addressbook.model.ContactData;
import ru.stqa.javacourse.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    if (! app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new GroupData("Test1", null, null));
    }
    app.getNavigationHelper().gotoToHomePage();
    List<ContactData> before = app.getContactHelper().getContactsList();
    ContactData contact = new ContactData("Test", "Test", "Test", "TTT", "test", "testcompany", "Russia", "+71231234578", "+79876543210", "test@test.com", "Test1");
    app.getContactHelper().createContact(contact);
    List <ContactData> after = app.getContactHelper().getContactsList();
    Assert.assertEquals(after.size(), before.size() + 1);

    int max = 0;
    for (ContactData c : after) {
      if (c.getId() > max) {
        max = c.getId();
      }
    }
    contact.setId(max);
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }

}
