// IBookManager.aidl
package ac692x_case.jieli.com.ipcapplication.aidl;

import ac692x_case.jieli.com.ipcapplication.aidl.Book;
import ac692x_case.jieli.com.ipcapplication.aidl.User;
import ac692x_case.jieli.com.ipcapplication.aidl.IMyAidlInterface;


// Declare any non-default types here with import statements

interface IBookManager {
    List<Book> getBooks();
    void addBook(in  Book book);


    void setUser(in User user);
    User getUser();



    void registerListener(IMyAidlInterface listener);
    void unregisterListener(IMyAidlInterface listener);
}
