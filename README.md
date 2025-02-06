# **My Exam**

No clue what it will be yet!.

---

## **Table of Contents**

1. [Installation](#installation)
   - [Frontend Setup](#frontend-setup)
   - [Backend Setup](#backend-setup)
2. [Usage](#usage)
3. [Technologies](#technologies)

---

## **Installation**

To get Exam up and running locally, follow these steps:

### **Frontend Setup**

1. Clone the repository:
   ```bash
   git clone https://github.com/Stalkakuma/exam.git
   ```
2. Navigate to the frontend directory:
   ```bash
   cd exam/exam-ui
   ```
3. Install dependencies:
   ```bash
   npm install
   ```
4. Start the development server:
   ```bash
   npm run dev
   ```

The React-Vite app will run at http://localhost:5173.

### **Backend Setup**

1. Navigate to the backend directory:
   ```bash
   cd exam/exam_api
   ```
2. Build the project:
   ```bash
   ./mvnw clean install
   ```
3. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```

The backend server will run at http://localhost:8080.

## **Usage**

1. Open the frontend in your browser: [http://localhost:5173](http://localhost:5173).
2. Documentation can be found on http://localhost:8080/swagger-ui/index.html

   Users =

   1. acc. name: admin; password: admin
   2. acc. name: user; password: user

## **Technologies**

### **Frontend**

- [React-Vite](https://vitejs.dev/)
- [React Bootstrap](https://react-bootstrap.github.io/)
- [Axios](https://axios-http.com/)

### **Backend**

- [Java Spring Boot](https://spring.io/projects/spring-boot)
- [H2 Database](https://www.h2database.com/)
- REST API architecture
# exam_template
# exam_template
