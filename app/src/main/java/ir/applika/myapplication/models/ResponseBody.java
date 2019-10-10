package ir.applika.myapplication.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseBody {
    int status = 0;
    @Expose
    @SerializedName("suggest")
    List<Book> suggestedBooks;
    @Expose
    @SerializedName("new")
    List<Book> newBooks;

    @Expose
    @SerializedName("pay")
    List<Book> payedBooks;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Book> getSuggestedBooks() {
        return suggestedBooks;
    }

    public void setSuggestedBooks(List<Book> suggestedBooks) {
        this.suggestedBooks = suggestedBooks;
    }

    public List<Book> getNewBooks() {
        return newBooks;
    }

    public void setNewBooks(List<Book> newBooks) {
        this.newBooks = newBooks;
    }

    public List<Book> getPayedBooks() {
        return payedBooks;
    }

    public void setPayedBooks(List<Book> payedBooks) {
        this.payedBooks = payedBooks;
    }
}
