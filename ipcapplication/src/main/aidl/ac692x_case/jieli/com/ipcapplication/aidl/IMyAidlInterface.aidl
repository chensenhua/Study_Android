// IMyAidlInterface.aidl
package ac692x_case.jieli.com.ipcapplication.aidl;
import ac692x_case.jieli.com.ipcapplication.aidl.Book;
// Declare any non-default types here with import statements

interface IMyAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void onBookChange(in List<Book> books);
}
