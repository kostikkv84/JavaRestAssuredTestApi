package api;
    // данные из блока Data
public class UserData {
    //заводим переменные такие как в респонсе
    private Integer id;
    private String email;
    private String first_name;
    private String last_name;
    private String avatar;


        /**
         * Создаем конструктор класса
         * @param id
         * @param email
         * @param first_name
         * @param last_name
         * @param avatar
         */
        public UserData(Integer id, String email, String first_name, String last_name, String avatar) {
            this.id = id;
            this.email = email;
            this.first_name = first_name;
            this.last_name = last_name;
            this.avatar = avatar;
        }

        /**
         * Пустой конструктор, для исправления ошибки: com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Cannot construct instance of
         */
        public UserData () {
            super();
        }

        /**
         * создаем геттеры для извлечения данных в переменные
          */
        public Integer getId() {
            return id;
        }

        public String getEmail() {
            return email;
        }

        public String getFirst_name() {
            return first_name;
        }

        public String getLast_name() {
            return last_name;
        }

        public String getAvatar() {
            return avatar;
        }


    }
