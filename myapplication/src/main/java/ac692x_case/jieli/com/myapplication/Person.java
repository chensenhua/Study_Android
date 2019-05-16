package ac692x_case.jieli.com.myapplication;

import ac692x_case.jieli.com.annotlib.AutoParcel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

@AutoParcel
public abstract class Person {
    @Nullable
    public String name;
    public int age;

    public static Person create(@NonNull String name, int age) {
      return new Person (){

      };
    }
}