import React from 'react';

import { makeStyles } from '@material-ui/core/styles';
import Grid from '@material-ui/core/Grid';
import Paper from '@material-ui/core/Paper';
import Typography from '@material-ui/core/Typography';
// import * as d3 from 'd3';

import IngredientTypeExpansionPanel from './Pantry';
import RecipeButton from "./FindRecipes";
import FilterPanel from "./Filters";
import UpdateDBPantry from "./UpdateDBPantry";
import MenuBar from "./MenuBar";
import Button from "@material-ui/core/Button";

// const ingredients = new Map();
//
// const typeMap = new Map();
// typeMap.set("Poultry", "Poultry & Eggs");
// typeMap.set("Seafood", "Fish & Seafood");
// typeMap.set("Meat", "Red Meat");
// typeMap.set("Vegetables", "Vegetables & Herbs");
// typeMap.set("Fruit", "Fruits & Berries");
// typeMap.set("Dairy", "Dairy & Dairy Alternatives");
// typeMap.set("Grains", "Grains, Breads, Pasta & Cereal");
// typeMap.set("Spices", "Spices & Seasonings");
// typeMap.set("Condiments", "Condiments, Oils & Sauces");
// typeMap.set("Sweeteners", "Sweeteners");
// typeMap.set("Nuts", "Nuts, Seeds & Legumes");
// typeMap.set("Beverages", "Beverages & Alcohol");
// typeMap.set("Baking", "Baking, Sweets & Snacks");
// typeMap.set("Soups", "Soups, Broth & Canned Goods");
//
// const types = Array.from(typeMap.values());
//
// for (let i in types) {
//     ingredients.set(types[i], []);
// }
//
// let ingredients_csv = require("./ingredients.csv");
//
// function extractIngredients() {
//     d3.csv(ingredients_csv).then(function(data) {
//         for (let i = 0; i < data.length; i++) {
//             ingredients.get(typeMap.get(data[i].Type)).push(data[i].Ingredient);
//         }
//     });
// }

// Ingredients & Ingredient Types
// TODO: use csv parser to populate map from 'ingredients.csv'
const ingredients = new Map();
const types = ['Poultry & Eggs','Fish & Seafood','Red Meat','Vegetables & Herbs','Fruits & Berries',
    'Dairy & Dairy Alternatives','Grains, Breads, Pasta & Cereal','Spices & Seasonings',
    'Condiments, Oils & Sauces','Sweeteners','Nuts, Seeds & Legumes','Beverages & Alcohol',
    'Baking, Sweets & Snacks','Soups, Broth & Canned Goods'];
ingredients.set('Poultry & Eggs',['Chicken Breast', 'Chicken Giblets', 'Chicken Leg',
    'Chicken Roast', 'Chicken Tenders', 'Chicken Thighs', 'Chicken Wings',
    'Cooked Chicken', 'Cornish Hen', 'Duck', 'Duck Liver', 'Eggs', 'Foie Gras',
    'Goose', 'Goose Liver', 'Grouse', 'Ostrich', 'Ostrich Eggs', 'Pheasant',
    'Pigeon', 'Quail', 'Quail Eggs', 'Turkey', 'Turkey Liver', 'Whole Chicken']);
ingredients.set('Fish & Seafood',['Amberjack', 'Anchovy', 'Arctic Char', 'Barramundi',
    'Bluefish', 'Calamari', 'Canned Salmon', 'Canned Tuna', 'Carp', 'Catfish',
    'Caviar', 'Clam', 'Cockle', 'Cod', 'Conch', 'Crab', 'Crawfish', 'Cuttlefish',
    'Eel', 'Fish Fillets', 'Flounder', 'Grouper', 'Haddock', 'Halibut', 'Herring',
    'John Dory', 'Lemon Sole', 'Lobster', 'Mackerel', 'Mahi Mahi', 'Marlin',
    'Monkfish', 'Mussel', 'Octopus', 'Oyster', 'Perch', 'Pike', 'Pollock',
    'Pompano', 'Prawns', 'Red Snapper', 'Rockfish', 'Salmon', 'Sardines',
    'Scallop', 'Sea Bass', 'Sea Urchin', 'Shrimp', 'Smoked Salmon', 'Sole',
    'Squid', 'Sturgeon', 'Swordfish', 'Tilapia', 'Trout', 'Tuna Steak', 'Whitefish']);
ingredients.set('Red Meat', ['Alligator', 'Bacon', 'Beef Liver', 'Beef Ribs', 'Beef Roast',
    'Beef Shank', 'Beef Sirloin', 'Beef Steak', 'Beef Suet', 'Bologna', 'Bratwurst',
    'Canadian Bacon', 'Chorizo', 'Corned Beef', 'Deer', 'Elk', 'Ground Beef',
    'Ground Lamb', 'Ground Pork', 'Ground Veal', 'Ham', 'Hot Dog', 'Lamb',
    'Lamb Chops', 'Lamb Liver', 'Lamb Loin', 'Lamb Shank', 'Lamb Shoulder',
    'Leg of Lamb', 'Liver Sausage', 'Marrow Bones', 'Moose', 'Oxtail', 'Pancetta',
    'Pastrami', 'Pepperoni', 'Polish Sausage', 'Pork', 'Pork Belly', 'Pork Chops',
    'Pork Liver', 'Pork Loin', 'Pork Ribs', 'Pork Roast', 'Pork Shoulder',
    'Prosciutto', 'Rabbit', 'Salami', 'Sausage', 'Soppressata', 'Spam', 'Sweetbread',
    'Veal', 'Veal Chops', 'Veal Cutlet', 'Veal Shank', 'Venison', 'Wild Boar']);
ingredients.set('Vegetables & Herbs',['Artichoke', 'Artichoke Heart', 'Arugula', 'Asparagus',
    'Bamboo Shoot', 'Basil', 'Bean Sprouts', 'Beet', 'Bell Pepper', 'Bok Choy',
    'Broccoli', 'Broccoli Rabe', 'Brussels Sprout', 'Burdock', 'Butternut',
    'Butternut Squash', 'Cabbage', 'Caper', 'Capsicum', 'Carrot', 'Cauliflower',
    'Celery', 'Celery Root', 'Chard', 'Chayote', 'Chili Pepper', 'Chinese Broccoli',
    'Chive', 'Cilantro', 'Collard', 'Corn', 'Cress', 'Cucumber', 'Daikon', 'Dill',
    'Dulse', 'Eggplant', 'Endive', 'Fennel', 'Frozen Vegetables', 'Garlic', 'Ginger',
    'Green Beans', 'Hearts Of Palm', 'Horseradish', 'Jerusalem Artichoke', 'Jicama',
    'Kale', 'Kohlrabi', 'Leek', 'Micro Greens', 'Mint', 'Mixed Vegetable', 'Mushroom',
    'Mustard Greens', 'Okra', 'Olive', 'Onion', 'Parsley', 'Parsnip', 'Pickle',
    'Pimiento', 'Porcini', 'Portobello Mushroom', 'Potato', 'Pumpkin', 'Radicchio',
    'Radish', 'Red Onion', 'Rocket', 'Rosemary', 'Rutabaga', 'Salad Greens',
    'Sauerkraut', 'Scallion', 'Seaweed', 'Shallot', 'Snow Peas', 'Spaghetti Squash',
    'Spinach', 'Squash', 'Sweet Pepper', 'Sweet Potato', 'Thyme', 'Turnip',
    'Water Chestnut', 'Watercress', 'Yam', 'Zucchini']);
ingredients.set('Fruits & Berries',['Apple', 'Apricot', 'Avocado', 'Banana', 'Berries',
    'Blackberry', 'Blueberry', 'Boysenberry', 'Cantaloupe', 'Cherry', 'Clementine',
    'Coconut', 'Crabapples', 'Craisins', 'Cranberry', 'Currant', 'Date', 'Fig',
    'Grape', 'Grapefruit', 'Guava', 'Honeydew', 'Kiwi', 'Kumquat', 'Lemon', 'Lime',
    'Lingonberry', 'Lychee', 'Mandarin', 'Mango', 'Nectarine', 'Orange', 'Papaya',
    'Passion Fruit', 'Peach', 'Pear', 'Persimmons', 'Pineapple', 'Plantain', 'Plum',
    'Pomegranate', 'Prunes', 'Quince', 'Raisin', 'Raspberry', 'Rhubarb', 'Star Fruit',
    'Strawberry', 'Sultanas', 'Sun Dried Tomato', 'Tangelos', 'Tangerine', 'Tomatillo',
    'Tomato', 'Watermelon']);
ingredients.set('Dairy & Dairy Alternatives',['Almond Milk', 'American Cheese', 'Asiago',
    'Blue Cheese', 'Bocconcini Cheese', 'Brick Cheese', 'Brie', 'Butter', 'Buttermilk',
    'Camembert Cheese', 'Cheddar', 'Cheshire Cheese', 'Coconut Milk', 'Colby Cheese',
    'Condensed Milk', 'Cottage Cheese', 'Cream', 'Cream Cheese', 'Creme De Cassis',
    'Creme Fraiche', 'Custard', 'Double Gloucester Cheese', 'Edam Cheese',
    'Emmenthaler Cheese', 'Evaporated Milk', 'Farmer Cheese', 'Feta', 'Fontina',
    'Frosting', 'Garlic Herb Cheese', 'Ghee', 'Goat Cheese', 'Goat Milk', 'Gouda',
    'Gruyere', 'Half And Half', 'Halloumi', 'Hard Cheese', 'Havarti Cheese',
    'Hemp Milk', 'Ice Cream', 'Italian Cheese', 'Jarlsberg Cheese', 'Lancashire Cheese',
    'Longhorn Cheese', 'Manchego', 'Margarine', 'Mascarpone', 'Milk',
    'Monterey Jack Cheese', 'Mozzarella', 'Muenster', 'Neufchatel', 'Non Dairy Creamer',
    'Paneer', 'Parmesan', 'Pecorino Cheese', 'Pepper Jack', 'Pepperjack Cheese',
    'Pizza Cheese', 'Powdered Milk', 'Provolone', 'Queso Fresco Cheese',
    'Raclette Cheese', 'Red Leicester Cheese', 'Rice Milk', 'Ricotta', 'Romano',
    'Soft Cheese', 'Sour Cream', 'Soy Milk', 'Stilton Cheese', 'Swiss Cheese',
    'Velveeta', 'Wensleydale Cheese', 'Whipped Cream', 'Yogurt']);
ingredients.set('Grains, Breads, Pasta & Cereal',['Angel Hair', 'Bagel', 'Baguette', 'Barley',
    'Biscuits', 'Bran', 'Bread', 'Bread Crumbs', 'Breadsticks', 'Brown Rice',
    'Buckwheat', 'Cereal', 'Challah', 'Ciabatta', 'Corn Tortillas', 'Cornbread',
    'Couscous', 'Cream Of Wheat', 'Croissants', 'Croutons', 'English Muffin',
    'Flour Tortillas', 'Gnocchi', 'Hot Dog Bun', 'Lasagna', 'Macaroni Cheese Mix',
    'Matzo Meal', 'Multigrain Bread', 'Noodle', 'Pasta', 'Pierogi', 'Pita',
    'Pizza Dough', 'Polenta', 'Popcorn', 'Pretzel', 'Quinoa', 'Ramen', 'Ravioli',
    'Rice', 'Risotto', 'Rolled Oats', 'Spelt', 'Vermicelli', 'Wafer', 'Wheat',
    'Wheat Germ']);
ingredients.set('Spices & Seasonings',['Adobo Seasoning', 'Allspice', 'Aniseed',
    'Balsamic Glaze', 'Bay Leaf', 'Bbq Rub', 'Bouillon', 'Brine', 'Cacao',
    'Cajun Seasoning', 'Caraway', 'Cardamom', 'Caribbean Jerk Seasoning', 'Cassia',
    'Cayenne', 'Celery Salt', 'Celery Seed', 'Chile Powder', 'Chili Paste', 'Chili Powder',
    'Chili Sauce', 'Chinese Five Spice', 'Chipotle', 'Cinnamon', 'Clove', 'Coriander',
    'Cream Of Tartar', 'Cumin', 'Curry Powder', 'Dill Seed', 'Fennel Seed',
    'Fenugreek', 'Fish Stock', 'Garam Masala', 'Garlic Powder', 'Ground Coriander',
    'Ground Ginger', 'Ground Nutmeg', 'Herbes De Provence', 'Hoisin Sauce',
    'Italian Seasoning', 'Italian Spice', 'Jamaican Jerk Spice', 'Kasuri Methi',
    'Lavender', 'Lemon Balm', 'Liquid Smoke', 'Mango Powder', 'Marjoram',
    'Matcha Powder', 'Miso', 'Mustard Powder', 'Mustard Seed', 'Nutmeg',
    'Old Bay Seasoning', 'Onion Powder', 'Oregano', 'Paprika', 'Pepper', 'Peppercorn',
    'Pickling Salt', 'Pickling Spice', 'Poppy Seed', 'Poultry Seasoning',
    'Red Pepper Flake', 'Rice Wine', 'Rose Water', 'Saffron', 'Sage', 'Salt', 'Savory',
    'Sesame Seed', 'Soy Sauce', 'Star Anise', 'Steak Seasoning', 'Taco Seasoning',
    'Tamarind', 'Tarragon', 'Thyme', 'Turmeric', 'Wasabi']);
ingredients.set('Condiments, Oils & Sauces',['Adobo Sauce', 'Alfredo Sauce', 'Almond Oil',
    'Apple Cider Vinegar', 'Avocado Oil', 'Balsamic Vinegar', 'Barbecue Sauce',
    'Blue Cheese Dressing', 'Buffalo Sauce', 'Caesar Dressing', 'Canola Oil',
    'Champagne Vinegar', 'Cocktail Sauce', 'Coconut Oil', 'Cooking Spray',
    'Corn Oil', 'Cranberry Sauce', 'Cream Sauce', 'Curry Paste', 'Duck Sauce',
    'Enchilada Sauce', 'Fish Sauce', 'French Dressing', 'Grape Seed Oil',
    'Hazelnut Oil', 'Honey Mustard', 'Hot Sauce', 'Italian Dressing', 'Ketchup',
    'Lard', 'Marsala', 'Mayonnaise', 'Mirin', 'Mustard', 'Mustard Oil', 'Olive Oil',
    'Oyster Sauce', 'Palm Oil', 'Peanut Oil', 'Pesto', 'Picante Sauce',
    'Pistachio Oil', 'Ponzu', 'Ranch Dressing', 'Rice Vinegar', 'Safflower Oil',
    'Salad Dressing', 'Salsa', 'Sesame Dressing', 'Sesame Oil', 'Shortening',
    'Soybean Oil', 'Soy Sauce', 'Sriracha', 'Steak Sauce', 'Sunflower Oil',
    'Sweet And Sour Sauce', 'Tabasco', 'Taco Sauce', 'Tahini', 'Tartar Sauce',
    'Teriyaki', 'Thousand Island Dressing', 'Tomato Paste', 'Tomato Sauce',
    'Tzatziki Sauce', 'Vegetable Oil', 'Vinaigrette Dressing', 'Vinegar',
    'Walnut Oil', 'Wine Vinegar', 'Worcestershire', 'Yuzu Juice']);
ingredients.set('Sweeteners',['Agave Nectar', 'Artificial Sweetener', 'Brown Sugar',
    'Confectioners Sugar', 'Corn Syrup', 'Honey', 'Maple Syrup', 'Molasses',
    'Sugar']);
ingredients.set('Nuts, Seeds & Legumes',['Almond', 'Almond Butter', 'Black Beans',
    'Cannellini Beans', 'Cashew', 'Chestnut', 'Chia Seeds', 'Chickpea',
    'Chili Beans', 'Edamame', 'Fava Beans', 'Flaxseed', 'French Beans',
    'Great Northern Beans', 'Green Beans', 'Hazelnut', 'Hummus', 'Kidney Beans',
    'Lentil', 'Lima Beans', 'Macadamia', 'Macaroon', 'Navy Beans', 'Peanut',
    'Peanut Butter', 'Peas', 'Pecan', 'Pine Nut', 'Pinto Beans', 'Pistachio',
    'Praline', 'Red Beans', 'Refried Beans', 'Snap Peas', 'Soybeans', 'Split Peas',
    'Sunflower Seeds', 'Walnut']);
ingredients.set('Beverages & Alcohol',['Absinthe', 'Amaretto', 'Anisette', 'Apple Cider',
    'Apple Juice', 'Beer', 'Bitters', 'Bloody Mary', 'Bourbon', 'Brandy',
    'Burgundy Wine', 'Cabernet Sauvignon', 'Champagne', 'Cherry Juice',
    'Chocolate Liqueur', 'Chocolate Milk', 'Ciclon', 'Club Soda', 'Coffee',
    'Cognac', 'Coke', 'Cooking Wine', 'Cranberry Juice', 'Creme De Menthe',
    'Curacao', 'Dessert Wine', 'Drambuie', 'Espresso', 'Frangelico', 'Fruit Juice',
    'Gin', 'Ginger Ale', 'Grand Marnier', 'Grappa', 'Green Tea', 'Grenadine',
    'Irish Cream', 'Kahlua', 'Kool Aid', 'Lemonade', 'Limoncello', 'Liqueur',
    'Madeira Wine', 'Maraschino', 'Margarita Mix', 'Masala', 'Mountain Dew',
    'Orange Juice', 'Ouzo', 'Pepsi', 'Pineapple Juice', 'Port Wine',
    'Raspberry Liquor', 'Red Wine', 'Rum', 'Sake', 'Schnapps', 'Shaoxing Wine',
    'Sherry', 'Sparkling Wine', 'Sprite', 'Tea', 'Tequila', 'Tomato Juice',
    'Triple Sec', 'Vermouth', 'Vodka', 'Whiskey', 'White Wine']);
ingredients.set('Baking, Sweets & Snacks',['Amaretti Cookies', 'Angel Food', 'Apple Butter',
    'Apple Sauce', 'Apricot Jam', 'Baking Powder', 'Baking Soda', 'Bicarbonate Of Soda',
    'Biscotti Biscuit', 'Bisquick', 'Bittersweet Chocolate', 'Blackberry Preserves',
    'Black Pudding', 'Blueberry Jam', 'Bread Dough', 'Bread Flour', 'Brownie Mix',
    'Butterscotch', 'Cake Mix', 'Caramel', 'Cherry Jam', 'Chili Jam', 'Chocolate',
    'Chocolate Bar', 'Chocolate Chips', 'Chocolate Powder', 'Chocolate Pudding',
    'Chocolate Syrup', 'Chocolate Wafer', 'Coconut Flake', 'Coconut Flour',
    'Corn Chips', 'Cornflour', 'Cornmeal', 'Cornstarch', 'Cracker',
    'Crescent Roll Dough', 'Currant Jelly', 'Dark Chocolate', 'Fig Jam',
    'Filo Dough', 'Flour', 'Graham Cracker', 'Gram Flour', 'Grape Jelly',
    'Jalapeno Jelly', 'Jam', 'Jello', 'Lady Fingers', 'Lemon Jelly', 'Malt Extract',
    'Marshmallow', 'Mint Jelly', 'Muffin Mix', 'Nutella', 'Orange Jelly', 'Oreo',
    'Pancake Mix', 'Peach Preserves', 'Pie Crust', 'Plum Jam', 'Potato Chips',
    'Potato Starch', 'Pudding Mix', 'Puff Pastry', 'Quince Jelly', 'Raspberry Jam',
    'Red Pepper Jelly', 'Rice Flour', 'Rye Flour', 'Saltines', 'Self Rising Flour',
    'Semolina', 'Shortcrust Pastry', 'Sorghum Flour', 'Sourdough Starter',
    'Sponge Cake', 'Starch', 'Strawberry Jam', 'Stuffing Mix', 'Tapioca Flour',
    'Tapioca Starch', 'Tortilla Chips', 'Vanilla', 'White Chocolate',
    'Whole Wheat Flour', 'Yeast', 'Yeast Flake']);
ingredients.set('Soups, Broth & Canned Goods',['Beef Broth', 'Canned Beets', 'Canned Carrots', 'Canned Corn',
    'Canned Green Beans', 'Canned Peas', 'Canned Tomato', 'Canned Vegetables',
    'Celery Soup', 'Chicken Broth', 'Chicken Soup', 'Dashi', 'Lamb Stock',
    'Mushroom Soup', 'Onion Soup', 'Pork Stock', 'Tomato Soup', 'Veal Stock',
    'Vegetable Bouillon', 'Vegetable Soup', 'Vegetable Stock']);

// maps for pantry & expiration to push data to database
const pantry = new Map();
const expiration = new Map();

// // initialize pantry & expiration dates with false/null values
// // i.e. user does not have any ingredients and has not set
// // any expiration dates for those ingredients
for (let i = 0; i < types.length; i++) {
    for (let j = 0; j < ingredients.get(types[i]).length; j++) {
        pantry.set(ingredients.get(types[i])[j], false);
        expiration.set(ingredients.get(types[i])[j], null);
    }
}

// styles for this functional component
const useStyles = makeStyles((theme) => ({
    root: {
        flexGrow: 1,
        // backgroundImage: `url(https://www.larchwoodcanada.com/wp-content/uploads/larchwood-classic-cutting-board-large_7652.jpg)`,
        /*backgroundImage: `url(https://www.hillwoodproducts.com/wp-content/uploads/2015/03/wood1.jpg)`,
        // backgroundColor: '#bae0f7',
        backgroundPosition: 'center',
        backgroundSize: '100%',
        backgroundRepeat: 'repeat-y',*/
    },
    header: {
        padding: theme.spacing(2),
        // color: theme.palette.text.secondary,
        color: 'black',
        opacity: '85%',
    },
    body: {
        padding: theme.spacing(2),
    },
    pantryHeader: {
        display: 'flex',
        justifyContent: "space-between",
        alignItems: "center",
    },
    pantry: {
        padding: theme.spacing(2),
        color: theme.palette.text.secondary,
        opacity: '85%',
        height: '100%',
    },
    recipes: {
        padding: theme.spacing(2),
        opacity: '85%',
        color: theme.palette.text.secondary,
        height: '100%',
    },
    instructions: {
        color: theme.palette.text.secondary,
        textAlign: "left",
    },
    btn: {
        marginLeft: 5,
        "&:hover": {
            backgroundColor: '#3f51b5',
            cursor: 'default',
        }
    }
}));
// let cont = false;

const backend_url = "https://backend-dot-quarantine-chef-278622.wl.r.appspot.com/";

// function to get user ingredients and expiration dates from database to populate virtual pantry
// const getPantryFromDatabase = async () => {
async function getPantryFromDatabase() {
    let response = await fetch(backend_url + "get-pantry", {
        credentials: 'include',
        mode: 'no-cors'
    });
    let databasePantry = await response.json();

    for (let i = 0; i < databasePantry.ingredients.length; i++) {
        pantry.set(databasePantry.ingredients[i].name, true);
        if (databasePantry.expirations[i] !== "null") {
            const parts = databasePantry.expirations[i].split("-");
            const date = new Date(+parts[2], +parts[1] - 1, +parts[0]);
            expiration.set(databasePantry.ingredients[i].name, date);
        }
    }
    // cont = true;
}

// This function returns the overall layout for the web app
export default function Layout() {
    // use styles for this functional component
    const classes = useStyles();
    let filterData = {};
    //let isLoggedIn = false;
    const [isLoggedIn, logIn] = React.useState(false);

    const handleFilterData = (data) => {
        filterData = data;
    }

    const getFilterData = (data) => {
        return filterData;
    }

    const handleUserUpdate = async () => {
        await getPantryFromDatabase();
        logIn(true);
    }

    // fetch('http://localhost:4567/get-pantry?userId=0')
    //     .then(response => response.json())
    //     .then(data => () => {
    //         for (let i = 0; i < data.ingredients.length; i++) {
    //             pantry.set(data.ingredients[i].name, true);
    //             expiration.set(data.ingredients[i].name, data.expirations[i])
    //         }
    //         console.log(pantry);
    //         console.log(expiration);
    //     });

    // extractIngredients();

    // initialize pantry & expiration dates with false/null values
    // i.e. user does not have any ingredients and has not set
    // any expiration dates for those ingredients
    // for (let i = 0; i < types.length; i++) {
    //     for (let j = 0; j < ingredients.get(types[i]).length; j++) {
    //         pantry.set(ingredients.get(types[i])[j], false);
    //         expiration.set(ingredients.get(types[i])[j], null);
    //     }
    // }

    // console.log(ingredients);

    // call database to update user pantry
    // getPantryFromDatabase();

    // return page elements
    return (
        <div className={classes.root}>
            <MenuBar handleUserUpdate={handleUserUpdate} />
            <Grid className={classes.body} container spacing={3} justify="flex-end">
                <Grid item xs={12}>
                    <Paper className={classes.header}>
                        <Typography className={classes.instructions}
                                    variant={"h5"}>
                            Welcome to QuarantineChef, where finding a recipe is easy:
                        </Typography>
                        <Typography className={classes.instructions}
                                    variant={"body1"}>
                            1) Please Sign In using Google account
                        </Typography>
                        <Typography className={classes.instructions}
                                    variant={"body1"}>
                            2) Add ingredients to your virtual pantry & click
                            <Button className={classes.btn}
                                    variant="contained"
                                    color="primary"
                                    disableElevation
                                    size="small">
                                Update Pantry
                            </Button>
                        </Typography>
                        <Typography className={classes.instructions}
                                    variant={"body1"}>
                            3) Select from our recipe filtering options!
                        </Typography>
                        <Typography className={classes.instructions}
                                    variant={"body1"}>
                            4) Click
                            <Button className={classes.btn}
                                    variant="contained"
                                    color="primary"
                                    disableElevation
                                    size="small">
                                Find Recipes
                            </Button>
                            , and your customized recipe recommendations will appear!
                        </Typography>
                        <Typography className={classes.instructions}
                                    variant={"h6"}>
                            It's as easy as that. Give it a try!
                        </Typography>
                    </Paper>
                </Grid>

                { isLoggedIn ?
                <Grid item xs={12} md={5}>
                    <Paper className={classes.pantry}>
                        <div className={classes.pantryHeader}>
                            <Typography variant="h5" gutterBottom>
                                PANTRY
                            </Typography>
                            <UpdateDBPantry types={types}
                                            ingredients={ingredients}
                                            pantry={pantry}
                                            expiration={expiration}/>
                        </div>
                        <IngredientTypeExpansionPanel types={types}
                                                      ingredients={ingredients}
                                                      pantry={pantry}
                                                      expiration={expiration}/>
                    </Paper>
                </Grid>
                    : null}
                { isLoggedIn ?
                <Grid item xs md>
                    <Paper className={classes.recipes}>
                        <FilterPanel handleFilterData={handleFilterData} />
                        <RecipeButton getFilterData={getFilterData}/>
                    </Paper>
                </Grid>
                    : null}
                <Grid item xs={12}> </Grid>

            </Grid>
        </div>
    );
}
