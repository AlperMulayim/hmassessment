# H&M Assesment - Alper Mulayim
H&amp;M Java Developer Assesment - Dress Recommendation System

- [Case Study Report Pdf](CaseStudy_Report_AlperMulayim.pdf)
- [Postman Collection](HM_Dress_Recommender_Alper_Mulayim.postman_collection.json)

Project Docker Container: 
`````java
  docker compose build
  docker compose up
`````

Swagger Url: http://localhost:8080/swagger-ui/index.html

``````java
POST http://localhost:8080/api/v1/recommendations/recipes
 {
    "totalBudget":500, //total budget
    "recipeName": "wedding", // type of event
    "numOfRecipe":3, // total number of recipes requested 
    "userId":1, // request user id if id is not provided or not valid user will return system default recommendation 
    "pricePreferences":{  //user preferences for budget percentages
        "top": 50, // %50
        "bottom": 30, //%30
        "accessories": 10, //%10
        "shoes": 20 // %20
    }
}
``````

