package ru.geekbrains.acquaintancewithandroid.hw.noteorganizer.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Note implements Parcelable {
    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };
    public String id;                   //уникальный идентификатор записи
    private String title;               //заголовок заметки
    private String content;             //содержимое заметки
    private Date createDate;            //дата и время создания заметки
    private Date updateDate;            //дата и время последнего редактирования заметки
    private String elemType;            //тип, заметки в данном случае
    private String elemView;            //вид заметки в данном случае
    private boolean marked;             //флаг состояния заметки (отмечена или не отмечена)

    public Note() {
    }

    public Note(String title, String content) {
        this.title = title;
        this.content = content;
    }

    protected Note(Parcel in) {
        id = in.readString();
        title = in.readString();
        content = in.readString();
        elemType = in.readString();
        elemView = in.readString();
        createDate = new Date(in.readLong());
        marked = in.readByte() != 0;
    }

    public String getElemType() {
        return elemType;
    }

    public void setElemType(String elemType) {
        this.elemType = elemType;
    }

    public String getElemView() {
        return elemView;
    }

    public void setElemView(String elemView) {
        this.elemView = elemView;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public boolean isMarked() {
        return marked;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(content);
        dest.writeString(elemType);
        dest.writeString(elemView);
        dest.writeLong(createDate.getTime());
        dest.writeByte((byte) (marked ? 1 : 0));
    }
}