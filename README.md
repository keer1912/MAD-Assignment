# 21 Chefs' Book
This is a recipe app that allows you to search for recipes and upload your own recipes for others to see.

## The Team:
### Xavier Teo              s10203441
### Keerthana Keshaini      s10194171 
### Sew Jing Wen            s10204931
### Sng Kai Ze Aaron        s10194187

## Contributions
### Xavier
* Add recipe page

* Search recipe page

* Helped members to debug & solve errors when needed

* Assited in App Testing

### Keerthana
* Initial Firebase Implementation

* OnBoard page 

* Home Page

* Recipe Viewing

* Standardised UI across app

* Bottom Navigation Implementation


### Jing Wen

### Aaron

## Appendices
[Our app on Google Play Store](https://play.google.com/store/apps/details?id=sg.edu.np.madassignment_chefsbook)

### Guide
[Oboarding](https://github.com/keer1912/MAD-Assignment#onboarding-page) | 
[Login](https://github.com/keer1912/MAD-Assignment#login) | 
[Home](https://github.com/keer1912/MAD-Assignment#home) | 
[Recipe View](https://github.com/keer1912/MAD-Assignment#recipe-view) | 
[Search](https://github.com/keer1912/MAD-Assignment#search) | 
[Add Recipe](https://github.com/keer1912/MAD-Assignment#add-recipe) | 
[Shopping List](https://github.com/keer1912/MAD-Assignment#shopping-list) | 
[Profile](https://github.com/keer1912/MAD-Assignment#profile)

#### Onboarding Page
This is a page that introduces Chefs' Book to first time users. Onboarding page serves as `walkthrough` page that demonstrates what the app does and set our users' expectations of the app. This Onboarding page has been implemented in a way that it checks for *first time users*. 

<p float="left">
  <img src="/README_Images/onboardpage1.png?raw=true" width="200">
  <img src="/README_Images/onboardpage2.png?raw=true" width="200">
  <img src="/README_Images/onboardpage3.png?raw=true" width="200">
</p>

#### Login
Proceeding from the Onboarding page , users will be greeted by the `login` page. Chefs' Book has integrated `Google Sign-in` into it's app. Our login utilises `Firebase` Authentication to autheticate our users' using their Google Accounts.

<img src="/README_Images/loginPage.png?raw=true" width="200">

#### Home
Once our users' have been authenticated , they will be led to the Home Page. In this page they will be able to see their names as well as various recipes curated for them. Featured recipes that allows them to `view` recipes that well liked by others as well as a popular category among many people nowadays , 30 minutes and Under.

<img src="/README_Images/homepage.png?raw=true" width="200">

#### Recipe View
When Chefs' Book users click on the recipe card as shown in the _Home Page_ or even _Search_ , they are led to the page where they will be able to view the recipe and the details. Details such as the recipe `name` , recipe `owner` , `Ingredients` , `Steps` and more!

<p float="left">
  <img src="/README_Images/recipeDetail.png?raw=true" width="200">
  <img src="/README_Images/recipeDetail2.png?raw=true" width="200">
</p>

#### Search
You can scroll to see the list of recipes or you can click on the search bar and type a recipe in to search for it
<p float="left">
    <img src="/README_Images/Screenshot_1627813980.png?raw=true" width="200">
    <img src="/README_Images/Screenshot_1627813994.png?raw=true" width="200">
    <img src="/README_Images/Screenshot_1627814005.png?raw=true" width="200">
</p>

#### Add Recipe
Start by clicking the floating Add button in the middle of the bottom navigation.

<img src="/README_Images/Screenshot_1627746290.png?raw=true" width="200">

Next, Click on `Upload Image` and select the image you want to upload.

<p float="left">
    <img src="/README_Images/Screenshot_1627747817.png?raw=true" width="200">
    <img src="/README_Images/Screenshot_1627747823.png?raw=true" width="200">
    <img src="/README_Images/Screenshot_1627747826.png?raw=true" width="200">
</p>
Type in your Recipe's `Name`, it's `Category` and a short `Description`. Your Recipe Name has a limit of 50 characters.

<img src="/README_Images/Screenshot_1627747900.png?raw=true" width="200">

Enter the `Time` in minutes using index numbers, decimals or other characters will be denied.

List the `Ingredients` your recipe require and the amount required \(not shown\) and seperate by pressing Enter.

<img src="/README_Images/Screenshot_1627747961.png?raw=true" width="200">

List `Equipments` needed, type in None if no equipment is required \[Hands only \:\)\]

Followed by the `Steps` to your amazing recipe, seperated by pressing Enter.

<img src="/README_Images/Screenshot_1627748100.png?raw=true" width="200">

Lastly, enter the `Serving Size` and click the Post button to submit your recipe

<img src="/README_Images/Screenshot_1627748109.png?raw=true" width="200">

Andddd... Walah! Your recipe is now uploaded into our App!

<p float="left">
    <img src="/README_Images/Screenshot_1627748137.png?raw=true" width="200">
    <img src="/README_Images/Screenshot_1627748140.png?raw=true" width="200">
</p>

#### Shopping List

#### Profile
