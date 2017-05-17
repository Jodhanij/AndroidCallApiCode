
package com.inheritx.simplewebservice.pojo;

public class SignUp {


    /**
     * success : 1
     * status : 1
     * message : Successfully Registration.
     * data : {"user_id":282,"user_email":"sachin1@inheritx.com","city":"Surat","country":"India","state":"Gujarat","first_name":"Samir","last_name":"Bhatt","gender":"male","address_1":"Vivek Residensy.","address_2":"Surat","zipcode":"380009","user_role_id":"1","weight":"88","height":"5\\' 11½ ","reach":"14½\" ","inside_leg":"27½\" ","measurement":"US","date_of_birth":"26-04-1992","user_profile_image":"http://inheritxdev.net/roula/wp-content/uploads/2016/06/device-2016-04-15-115520-3-150x150.png"}
     */

    private int success;
    private int status;
    private String message;
    /**
     * user_id : 282
     * user_email : sachin1@inheritx.com
     * city : Surat
     * country : India
     * state : Gujarat
     * first_name : Samir
     * last_name : Bhatt
     * gender : male
     * address_1 : Vivek Residensy.
     * address_2 : Surat
     * zipcode : 380009
     * user_role_id : 1
     * weight : 88
     * height : 5\' 11½
     * reach : 14½"
     * inside_leg : 27½"
     * measurement : US
     * date_of_birth : 26-04-1992
     * user_profile_image : http://inheritxdev.net/roula/wp-content/uploads/2016/06/device-2016-04-15-115520-3-150x150.png
     */

    private DataBean data;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private int user_id;
        private String user_email;
        private String city;
        private String country;
        private String state;
        private String first_name;
        private String last_name;
        private String gender;
        private String address_1;
        private String address_2;
        private String zipcode;
        private String user_role_id;
        private String weight;
        private String height;
        private String reach;
        private String inside_leg;
        private String measurement;
        private String date_of_birth;
        private String user_profile_image;

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getUser_email() {
            return user_email;
        }

        public void setUser_email(String user_email) {
            this.user_email = user_email;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getFirst_name() {
            return first_name;
        }

        public void setFirst_name(String first_name) {
            this.first_name = first_name;
        }

        public String getLast_name() {
            return last_name;
        }

        public void setLast_name(String last_name) {
            this.last_name = last_name;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getAddress_1() {
            return address_1;
        }

        public void setAddress_1(String address_1) {
            this.address_1 = address_1;
        }

        public String getAddress_2() {
            return address_2;
        }

        public void setAddress_2(String address_2) {
            this.address_2 = address_2;
        }

        public String getZipcode() {
            return zipcode;
        }

        public void setZipcode(String zipcode) {
            this.zipcode = zipcode;
        }

        public String getUser_role_id() {
            return user_role_id;
        }

        public void setUser_role_id(String user_role_id) {
            this.user_role_id = user_role_id;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getReach() {
            return reach;
        }

        public void setReach(String reach) {
            this.reach = reach;
        }

        public String getInside_leg() {
            return inside_leg;
        }

        public void setInside_leg(String inside_leg) {
            this.inside_leg = inside_leg;
        }

        public String getMeasurement() {
            return measurement;
        }

        public void setMeasurement(String measurement) {
            this.measurement = measurement;
        }

        public String getDate_of_birth() {
            return date_of_birth;
        }

        public void setDate_of_birth(String date_of_birth) {
            this.date_of_birth = date_of_birth;
        }

        public String getUser_profile_image() {
            return user_profile_image;
        }

        public void setUser_profile_image(String user_profile_image) {
            this.user_profile_image = user_profile_image;
        }
    }
}
