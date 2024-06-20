    package com.example.students.model;

    import android.os.Parcel;
    import android.os.Parcelable;

    import com.google.gson.annotations.Expose;
    import com.google.gson.annotations.SerializedName;

    public class TestQuestion implements Parcelable {
        @SerializedName("question")
        @Expose
        private String question;

        @SerializedName("f1")
        @Expose
        private String f1;

        @SerializedName("f2")
        @Expose
        private String f2;

        @SerializedName("f3")
        @Expose
        private String f3;

        @SerializedName("right")
        @Expose
        private int right;

        public TestQuestion(String question, String f1, String f2, String f3, int right) {
            this.question = question;
            this.f1 = f1;
            this.f2 = f2;
            this.f3 = f3;
            this.right = right;
        }

        protected TestQuestion(Parcel in) {
            question = in.readString();
            f1 = in.readString();
            f2 = in.readString();
            f3 = in.readString();
            right = in.readInt();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(question);
            dest.writeString(f1);
            dest.writeString(f2);
            dest.writeString(f3);
            dest.writeInt(right);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<TestQuestion> CREATOR = new Creator<TestQuestion>() {
            @Override
            public TestQuestion createFromParcel(Parcel in) {
                return new TestQuestion(in);
            }

            @Override
            public TestQuestion[] newArray(int size) {
                return new TestQuestion[size];
            }
        };

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getF1() {
            return f1;
        }

        public void setF1(String f1) {
            this.f1 = f1;
        }

        public String getF2() {
            return f2;
        }

        public void setF2(String f2) {
            this.f2 = f2;
        }

        public String getF3() {
            return f3;
        }

        public void setF3(String f3) {
            this.f3 = f3;
        }

        public int getRight() {
            return right;
        }

        public void setRight(int right) {
            this.right = right;
        }
    }
