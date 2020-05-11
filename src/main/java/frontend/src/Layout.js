import React from 'react';
// import {Component} from 'react';

import { makeStyles } from '@material-ui/core/styles';
// import { withStyles } from '@material-ui/styles';
import Grid from '@material-ui/core/Grid';
import Paper from '@material-ui/core/Paper';
import Typography from '@material-ui/core/Typography';

import SimpleExpansionPanel from './pantry';
import RecipeButton from "./generateRecipes";
import Logo from "./logo"
import UpdatePantry from "./updatePantry";

const types = ['Poultry & Eggs','Fish & Seafood','Red Meat','Vegetables & Herbs','Fruits & Berries',
    'Dairy & Dairy Alternatives','Grains, Breads, Pasta & Cereal','Spices & Seasonings',
    'Condiments, Oils & Sauces','Sweeteners','Nuts, Seeds & Legumes','Beverages & Alcohol',
    'Baking, Sweets & Snacks','Soups, Broth & Canned Goods'];

const ingredients = new Map();
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
    'Old Bay Seasoning', 'Onion Powder', 'Oregano', 'Paprika', 'Peppercorn',
    'Pickling Salt', 'Pickling Spice', 'Poppy Seed', 'Poultry Seasoning',
    'Red Pepper Flake', 'Rice Wine', 'Rose Water', 'Saffron', 'Sage', 'Savory',
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

const pantry = new Map();
const expiration = new Map();

const useStyles = makeStyles((theme) => ({
    root: {
        flexGrow: 1,
        backgroundImage: `url(https://thepaintpeople.com/wp-content/uploads/2015/09/prepare-bare-wood-staining.jpg)`,
        backgroundPosition: 'center',
        backgroundSize: '100%',
        backgroundRepeat: 'repeat-y',
    },
    header: {
        padding: theme.spacing(2),
        textAlign: 'center',
        color: theme.palette.text.secondary,
        // background: 'linear-gradient(45deg, #FE6B8B 30%, #FF8E53 90%)',
        // background: 'linear-gradient(0.25turn, #3f87a6, #ebf8e1, #f69d3c)',
    },
    pantryHeader: {
        flexDirection: "row",
        justifyContent: "space-between",
        minWidth: 500,
    },
    pantry: {
        padding: theme.spacing(2),
        textAlign: 'center',
        color: theme.palette.text.secondary,
        opacity: '85%',
        height: '100%',
        minWidth: 500,
    },
    recipes: {
        padding: theme.spacing(2),
        opacity: '85%',
        color: theme.palette.text.secondary,
        height: '100%',
    },
}));

const getPantryFromDatabase = async () => {
// const getPantryFromDatabase = () => {
    // fetch("http://localhost:4567/get-pantry?userId=0")
    //     .then(res => res.json())
    //     .then(json => {
    //         for (let i = 0; i < json.ingredients.length; i++) {
    //             pantry.set(json.ingredients[i].name, true);
    //             pantry.set(json.ingredients[i].name, json.expirations[i])
    //         }
    // })
    let response = await fetch("http://localhost:4567/get-pantry?userId=0")
    let databasePantry = await response.json();
    for (let i = 0; i < databasePantry.ingredients.length; i++) {
        pantry.set(databasePantry.ingredients[i].name, true);
        pantry.set(databasePantry.ingredients[i].name, databasePantry.expirations[i])
    }
}

for (let i = 0; i < types.length; i++) {
    for (let j = 0; j < ingredients.get(types[i]).length; j++) {
        pantry.set(ingredients.get(types[i])[j], false);
        expiration.set(ingredients.get(types[i])[j], null);
    }
}

getPantryFromDatabase();

export default function Layout() {
    const classes = useStyles();
    // for (let i = 0; i < types.length; i++) {
    //     for (let j = 0; j < ingredients.get(types[i]).length; j++) {
    //         pantry.set(ingredients.get(types[i])[j], false);
    //         expiration.set(ingredients.get(types[i])[j], null);
    //     }
    // }
    // getPantryFromDatabase();
    // fetch("http://localhost:4567/get-pantry?userId=0")
    //     .then(res => res.json())
    //     .then(json => {
    //         for (let i = 0; i < json.ingredients.length; i++) {
    //             pantry.set(json.ingredients[i].name, true);
    //             pantry.set(json.ingredients[i].name, json.expirations[i])
    //         }
    //     });

    return (
        <div className={classes.root}>
            <Grid container spacing={3} justify="flex-end">
                <Grid item xs={12}>
                    <Paper className={classes.header}>
                        <Logo />
                    </Paper>
                </Grid>
                <Grid item xs={5}>
                    <Paper className={classes.pantry}>
                        <div className={classes.pantryHeader}>
                            <Typography variant="h5" gutterBottom>
                                Pantry
                            </Typography>
                            <UpdatePantry types={types}
                                          ingredients={ingredients}
                                          pantry={pantry}
                                          expiration={expiration}/>
                        </div>
                        <SimpleExpansionPanel types={types}
                                              ingredients={ingredients}
                                              pantry={pantry}
                                              expiration={expiration}/>
                    </Paper>
                </Grid>
                <Grid item xs={7}>
                    {/*<Paper className={classes.paper}>Filters</Paper>*/}
                    <Paper className={classes.recipes}>
                        <RecipeButton />
                    </Paper>
                </Grid>
                <Grid item xs={12}> </Grid>
            </Grid>
        </div>
    );
}

// const styles = theme => ({
//     root: {
//         flexGrow: 1,
//         // backgroundImage: `url(https://thepaintpeople.com/wp-content/uploads/2015/09/prepare-bare-wood-staining.jpg)`,
//         // backgroundPosition: 'center',
//         // backgroundSize: '100%',
//         // backgroundRepeat: 'repeat-y',
//     },
//     header: {
//         // padding: theme.spacing(2),
//         padding: 10,
//         textAlign: 'center',
//         // color: theme.palette.text.secondary,
//         // background: 'linear-gradient(45deg, #FE6B8B 30%, #FF8E53 90%)',
//         // background: 'linear-gradient(0.25turn, #3f87a6, #ebf8e1, #f69d3c)',
//     },
//     pantryHeader: {
//         flexDirection: "row",
//         justifyContent: "space-between",
//     },
//     pantry: {
//         // padding: theme.spacing(2),
//         padding: 10,
//         textAlign: 'center',
//         // color: theme.palette.text.secondary,
//         // opacity: '85%',
//         height: '100%',
//     },
//     recipes: {
//         // padding: theme.spacing(2),
//         padding: 10,
//         // opacity: '85%',
//         // color: theme.palette.text.secondary,
//         height: '100%',
//     },
// });
//
// class Layout extends Component {
//     constructor(props) {
//         super(props);
//         this.state = { types: this.getTypes(),
//                        ingredients: this.getIngredients(),
//                        pantry: this.getPantry(),
//                        expiration: this.getExpiration(),
//         };
//         this.getPantryFromDB = this.getPantryFromDB.bind(this);
//     }
//
//     getTypes() {
//         return types;
//         // return ['Poultry & Eggs','Fish & Seafood','Red Meat','Vegetables & Herbs','Fruits & Berries',
//         //     'Dairy & Dairy Alternatives','Grains, Breads, Pasta & Cereal','Spices & Seasonings',
//         //     'Condiments, Oils & Sauces','Sweeteners','Nuts, Seeds & Legumes','Beverages & Alcohol',
//         //     'Baking, Sweets & Snacks','Soups, Broth & Canned Goods'];
//     }
//
//     getIngredients() {
//         return ingredients;
//         // const ingredients = new Map();
//         // ingredients.set('Poultry & Eggs',['Chicken Breast', 'Chicken Giblets', 'Chicken Leg',
//         //     'Chicken Roast', 'Chicken Tenders', 'Chicken Thighs', 'Chicken Wings',
//         //     'Cooked Chicken', 'Cornish Hen', 'Duck', 'Duck Liver', 'Eggs', 'Foie Gras',
//         //     'Goose', 'Goose Liver', 'Grouse', 'Ostrich', 'Ostrich Eggs', 'Pheasant',
//         //     'Pigeon', 'Quail', 'Quail Eggs', 'Turkey', 'Turkey Liver', 'Whole Chicken']);
//         // ingredients.set('Fish & Seafood',['Amberjack', 'Anchovy', 'Arctic Char', 'Barramundi',
//         //     'Bluefish', 'Calamari', 'Canned Salmon', 'Canned Tuna', 'Carp', 'Catfish',
//         //     'Caviar', 'Clam', 'Cockle', 'Cod', 'Conch', 'Crab', 'Crawfish', 'Cuttlefish',
//         //     'Eel', 'Fish Fillets', 'Flounder', 'Grouper', 'Haddock', 'Halibut', 'Herring',
//         //     'John Dory', 'Lemon Sole', 'Lobster', 'Mackerel', 'Mahi Mahi', 'Marlin',
//         //     'Monkfish', 'Mussel', 'Octopus', 'Oyster', 'Perch', 'Pike', 'Pollock',
//         //     'Pompano', 'Prawns', 'Red Snapper', 'Rockfish', 'Salmon', 'Sardines',
//         //     'Scallop', 'Sea Bass', 'Sea Urchin', 'Shrimp', 'Smoked Salmon', 'Sole',
//         //     'Squid', 'Sturgeon', 'Swordfish', 'Tilapia', 'Trout', 'Tuna Steak', 'Whitefish']);
//         // ingredients.set('Red Meat', ['Alligator', 'Bacon', 'Beef Liver', 'Beef Ribs', 'Beef Roast',
//         //     'Beef Shank', 'Beef Sirloin', 'Beef Steak', 'Beef Suet', 'Bologna', 'Bratwurst',
//         //     'Canadian Bacon', 'Chorizo', 'Corned Beef', 'Deer', 'Elk', 'Ground Beef',
//         //     'Ground Lamb', 'Ground Pork', 'Ground Veal', 'Ham', 'Hot Dog', 'Lamb',
//         //     'Lamb Chops', 'Lamb Liver', 'Lamb Loin', 'Lamb Shank', 'Lamb Shoulder',
//         //     'Leg of Lamb', 'Liver Sausage', 'Marrow Bones', 'Moose', 'Oxtail', 'Pancetta',
//         //     'Pastrami', 'Pepperoni', 'Polish Sausage', 'Pork', 'Pork Belly', 'Pork Chops',
//         //     'Pork Liver', 'Pork Loin', 'Pork Ribs', 'Pork Roast', 'Pork Shoulder',
//         //     'Prosciutto', 'Rabbit', 'Salami', 'Sausage', 'Soppressata', 'Spam', 'Sweetbread',
//         //     'Veal', 'Veal Chops', 'Veal Cutlet', 'Veal Shank', 'Venison', 'Wild Boar']);
//         // ingredients.set('Vegetables & Herbs',['Artichoke', 'Artichoke Heart', 'Arugula', 'Asparagus',
//         //     'Bamboo Shoot', 'Basil', 'Bean Sprouts', 'Beet', 'Bell Pepper', 'Bok Choy',
//         //     'Broccoli', 'Broccoli Rabe', 'Brussels Sprout', 'Burdock', 'Butternut',
//         //     'Butternut Squash', 'Cabbage', 'Caper', 'Capsicum', 'Carrot', 'Cauliflower',
//         //     'Celery', 'Celery Root', 'Chard', 'Chayote', 'Chili Pepper', 'Chinese Broccoli',
//         //     'Chive', 'Cilantro', 'Collard', 'Corn', 'Cress', 'Cucumber', 'Daikon', 'Dill',
//         //     'Dulse', 'Eggplant', 'Endive', 'Fennel', 'Frozen Vegetables', 'Garlic', 'Ginger',
//         //     'Green Beans', 'Hearts Of Palm', 'Horseradish', 'Jerusalem Artichoke', 'Jicama',
//         //     'Kale', 'Kohlrabi', 'Leek', 'Micro Greens', 'Mint', 'Mixed Vegetable', 'Mushroom',
//         //     'Mustard Greens', 'Okra', 'Olive', 'Onion', 'Parsley', 'Parsnip', 'Pickle',
//         //     'Pimiento', 'Porcini', 'Portobello Mushroom', 'Potato', 'Pumpkin', 'Radicchio',
//         //     'Radish', 'Red Onion', 'Rocket', 'Rosemary', 'Rutabaga', 'Salad Greens',
//         //     'Sauerkraut', 'Scallion', 'Seaweed', 'Shallot', 'Snow Peas', 'Spaghetti Squash',
//         //     'Spinach', 'Squash', 'Sweet Pepper', 'Sweet Potato', 'Thyme', 'Turnip',
//         //     'Water Chestnut', 'Watercress', 'Yam', 'Zucchini']);
//         // ingredients.set('Fruits & Berries',['Apple', 'Apricot', 'Avocado', 'Banana', 'Berries',
//         //     'Blackberry', 'Blueberry', 'Boysenberry', 'Cantaloupe', 'Cherry', 'Clementine',
//         //     'Coconut', 'Crabapples', 'Craisins', 'Cranberry', 'Currant', 'Date', 'Fig',
//         //     'Grape', 'Grapefruit', 'Guava', 'Honeydew', 'Kiwi', 'Kumquat', 'Lemon', 'Lime',
//         //     'Lingonberry', 'Lychee', 'Mandarin', 'Mango', 'Nectarine', 'Orange', 'Papaya',
//         //     'Passion Fruit', 'Peach', 'Pear', 'Persimmons', 'Pineapple', 'Plantain', 'Plum',
//         //     'Pomegranate', 'Prunes', 'Quince', 'Raisin', 'Raspberry', 'Rhubarb', 'Star Fruit',
//         //     'Strawberry', 'Sultanas', 'Sun Dried Tomato', 'Tangelos', 'Tangerine', 'Tomatillo',
//         //     'Tomato', 'Watermelon']);
//         // ingredients.set('Dairy & Dairy Alternatives',['Almond Milk', 'American Cheese', 'Asiago',
//         //     'Blue Cheese', 'Bocconcini Cheese', 'Brick Cheese', 'Brie', 'Butter', 'Buttermilk',
//         //     'Camembert Cheese', 'Cheddar', 'Cheshire Cheese', 'Coconut Milk', 'Colby Cheese',
//         //     'Condensed Milk', 'Cottage Cheese', 'Cream', 'Cream Cheese', 'Creme De Cassis',
//         //     'Creme Fraiche', 'Custard', 'Double Gloucester Cheese', 'Edam Cheese',
//         //     'Emmenthaler Cheese', 'Evaporated Milk', 'Farmer Cheese', 'Feta', 'Fontina',
//         //     'Frosting', 'Garlic Herb Cheese', 'Ghee', 'Goat Cheese', 'Goat Milk', 'Gouda',
//         //     'Gruyere', 'Half And Half', 'Halloumi', 'Hard Cheese', 'Havarti Cheese',
//         //     'Hemp Milk', 'Ice Cream', 'Italian Cheese', 'Jarlsberg Cheese', 'Lancashire Cheese',
//         //     'Longhorn Cheese', 'Manchego', 'Margarine', 'Mascarpone', 'Milk',
//         //     'Monterey Jack Cheese', 'Mozzarella', 'Muenster', 'Neufchatel', 'Non Dairy Creamer',
//         //     'Paneer', 'Parmesan', 'Pecorino Cheese', 'Pepper Jack', 'Pepperjack Cheese',
//         //     'Pizza Cheese', 'Powdered Milk', 'Provolone', 'Queso Fresco Cheese',
//         //     'Raclette Cheese', 'Red Leicester Cheese', 'Rice Milk', 'Ricotta', 'Romano',
//         //     'Soft Cheese', 'Sour Cream', 'Soy Milk', 'Stilton Cheese', 'Swiss Cheese',
//         //     'Velveeta', 'Wensleydale Cheese', 'Whipped Cream', 'Yogurt']);
//         // ingredients.set('Grains, Breads, Pasta & Cereal',['Angel Hair', 'Bagel', 'Baguette', 'Barley',
//         //     'Biscuits', 'Bran', 'Bread', 'Bread Crumbs', 'Breadsticks', 'Brown Rice',
//         //     'Buckwheat', 'Cereal', 'Challah', 'Ciabatta', 'Corn Tortillas', 'Cornbread',
//         //     'Couscous', 'Cream Of Wheat', 'Croissants', 'Croutons', 'English Muffin',
//         //     'Flour Tortillas', 'Gnocchi', 'Hot Dog Bun', 'Lasagna', 'Macaroni Cheese Mix',
//         //     'Matzo Meal', 'Multigrain Bread', 'Noodle', 'Pasta', 'Pierogi', 'Pita',
//         //     'Pizza Dough', 'Polenta', 'Popcorn', 'Pretzel', 'Quinoa', 'Ramen', 'Ravioli',
//         //     'Rice', 'Risotto', 'Rolled Oats', 'Spelt', 'Vermicelli', 'Wafer', 'Wheat',
//         //     'Wheat Germ']);
//         // ingredients.set('Spices & Seasonings',['Adobo Seasoning', 'Allspice', 'Aniseed',
//         //     'Balsamic Glaze', 'Bay Leaf', 'Bbq Rub', 'Bouillon', 'Brine', 'Cacao',
//         //     'Cajun Seasoning', 'Caraway', 'Cardamom', 'Caribbean Jerk Seasoning', 'Cassia',
//         //     'Cayenne', 'Celery Salt', 'Celery Seed', 'Chile Powder', 'Chili Paste', 'Chili Powder',
//         //     'Chili Sauce', 'Chinese Five Spice', 'Chipotle', 'Cinnamon', 'Clove', 'Coriander',
//         //     'Cream Of Tartar', 'Cumin', 'Curry Powder', 'Dill Seed', 'Fennel Seed',
//         //     'Fenugreek', 'Fish Stock', 'Garam Masala', 'Garlic Powder', 'Ground Coriander',
//         //     'Ground Ginger', 'Ground Nutmeg', 'Herbes De Provence', 'Hoisin Sauce',
//         //     'Italian Seasoning', 'Italian Spice', 'Jamaican Jerk Spice', 'Kasuri Methi',
//         //     'Lavender', 'Lemon Balm', 'Liquid Smoke', 'Mango Powder', 'Marjoram',
//         //     'Matcha Powder', 'Miso', 'Mustard Powder', 'Mustard Seed', 'Nutmeg',
//         //     'Old Bay Seasoning', 'Onion Powder', 'Oregano', 'Paprika', 'Peppercorn',
//         //     'Pickling Salt', 'Pickling Spice', 'Poppy Seed', 'Poultry Seasoning',
//         //     'Red Pepper Flake', 'Rice Wine', 'Rose Water', 'Saffron', 'Sage', 'Savory',
//         //     'Sesame Seed', 'Soy Sauce', 'Star Anise', 'Steak Seasoning', 'Taco Seasoning',
//         //     'Tamarind', 'Tarragon', 'Thyme', 'Turmeric', 'Wasabi']);
//         // ingredients.set('Condiments, Oils & Sauces',['Adobo Sauce', 'Alfredo Sauce', 'Almond Oil',
//         //     'Apple Cider Vinegar', 'Avocado Oil', 'Balsamic Vinegar', 'Barbecue Sauce',
//         //     'Blue Cheese Dressing', 'Buffalo Sauce', 'Caesar Dressing', 'Canola Oil',
//         //     'Champagne Vinegar', 'Cocktail Sauce', 'Coconut Oil', 'Cooking Spray',
//         //     'Corn Oil', 'Cranberry Sauce', 'Cream Sauce', 'Curry Paste', 'Duck Sauce',
//         //     'Enchilada Sauce', 'Fish Sauce', 'French Dressing', 'Grape Seed Oil',
//         //     'Hazelnut Oil', 'Honey Mustard', 'Hot Sauce', 'Italian Dressing', 'Ketchup',
//         //     'Lard', 'Marsala', 'Mayonnaise', 'Mirin', 'Mustard', 'Mustard Oil', 'Olive Oil',
//         //     'Oyster Sauce', 'Palm Oil', 'Peanut Oil', 'Pesto', 'Picante Sauce',
//         //     'Pistachio Oil', 'Ponzu', 'Ranch Dressing', 'Rice Vinegar', 'Safflower Oil',
//         //     'Salad Dressing', 'Salsa', 'Sesame Dressing', 'Sesame Oil', 'Shortening',
//         //     'Soybean Oil', 'Soy Sauce', 'Sriracha', 'Steak Sauce', 'Sunflower Oil',
//         //     'Sweet And Sour Sauce', 'Tabasco', 'Taco Sauce', 'Tahini', 'Tartar Sauce',
//         //     'Teriyaki', 'Thousand Island Dressing', 'Tomato Paste', 'Tomato Sauce',
//         //     'Tzatziki Sauce', 'Vegetable Oil', 'Vinaigrette Dressing', 'Vinegar',
//         //     'Walnut Oil', 'Wine Vinegar', 'Worcestershire', 'Yuzu Juice']);
//         // ingredients.set('Sweeteners',['Agave Nectar', 'Artificial Sweetener', 'Brown Sugar',
//         //     'Confectioners Sugar', 'Corn Syrup', 'Honey', 'Maple Syrup', 'Molasses',
//         //     'Sugar']);
//         // ingredients.set('Nuts, Seeds & Legumes',['Almond', 'Almond Butter', 'Black Beans',
//         //     'Cannellini Beans', 'Cashew', 'Chestnut', 'Chia Seeds', 'Chickpea',
//         //     'Chili Beans', 'Edamame', 'Fava Beans', 'Flaxseed', 'French Beans',
//         //     'Great Northern Beans', 'Green Beans', 'Hazelnut', 'Hummus', 'Kidney Beans',
//         //     'Lentil', 'Lima Beans', 'Macadamia', 'Macaroon', 'Navy Beans', 'Peanut',
//         //     'Peanut Butter', 'Peas', 'Pecan', 'Pine Nut', 'Pinto Beans', 'Pistachio',
//         //     'Praline', 'Red Beans', 'Refried Beans', 'Snap Peas', 'Soybeans', 'Split Peas',
//         //     'Sunflower Seeds', 'Walnut']);
//         // ingredients.set('Beverages & Alcohol',['Absinthe', 'Amaretto', 'Anisette', 'Apple Cider',
//         //     'Apple Juice', 'Beer', 'Bitters', 'Bloody Mary', 'Bourbon', 'Brandy',
//         //     'Burgundy Wine', 'Cabernet Sauvignon', 'Champagne', 'Cherry Juice',
//         //     'Chocolate Liqueur', 'Chocolate Milk', 'Ciclon', 'Club Soda', 'Coffee',
//         //     'Cognac', 'Coke', 'Cooking Wine', 'Cranberry Juice', 'Creme De Menthe',
//         //     'Curacao', 'Dessert Wine', 'Drambuie', 'Espresso', 'Frangelico', 'Fruit Juice',
//         //     'Gin', 'Ginger Ale', 'Grand Marnier', 'Grappa', 'Green Tea', 'Grenadine',
//         //     'Irish Cream', 'Kahlua', 'Kool Aid', 'Lemonade', 'Limoncello', 'Liqueur',
//         //     'Madeira Wine', 'Maraschino', 'Margarita Mix', 'Masala', 'Mountain Dew',
//         //     'Orange Juice', 'Ouzo', 'Pepsi', 'Pineapple Juice', 'Port Wine',
//         //     'Raspberry Liquor', 'Red Wine', 'Rum', 'Sake', 'Schnapps', 'Shaoxing Wine',
//         //     'Sherry', 'Sparkling Wine', 'Sprite', 'Tea', 'Tequila', 'Tomato Juice',
//         //     'Triple Sec', 'Vermouth', 'Vodka', 'Whiskey', 'White Wine']);
//         // ingredients.set('Baking, Sweets & Snacks',['Amaretti Cookies', 'Angel Food', 'Apple Butter',
//         //     'Apple Sauce', 'Apricot Jam', 'Baking Powder', 'Baking Soda', 'Bicarbonate Of Soda',
//         //     'Biscotti Biscuit', 'Bisquick', 'Bittersweet Chocolate', 'Blackberry Preserves',
//         //     'Black Pudding', 'Blueberry Jam', 'Bread Dough', 'Bread Flour', 'Brownie Mix',
//         //     'Butterscotch', 'Cake Mix', 'Caramel', 'Cherry Jam', 'Chili Jam', 'Chocolate',
//         //     'Chocolate Bar', 'Chocolate Chips', 'Chocolate Powder', 'Chocolate Pudding',
//         //     'Chocolate Syrup', 'Chocolate Wafer', 'Coconut Flake', 'Coconut Flour',
//         //     'Corn Chips', 'Cornflour', 'Cornmeal', 'Cornstarch', 'Cracker',
//         //     'Crescent Roll Dough', 'Currant Jelly', 'Dark Chocolate', 'Fig Jam',
//         //     'Filo Dough', 'Flour', 'Graham Cracker', 'Gram Flour', 'Grape Jelly',
//         //     'Jalapeno Jelly', 'Jam', 'Jello', 'Lady Fingers', 'Lemon Jelly', 'Malt Extract',
//         //     'Marshmallow', 'Mint Jelly', 'Muffin Mix', 'Nutella', 'Orange Jelly', 'Oreo',
//         //     'Pancake Mix', 'Peach Preserves', 'Pie Crust', 'Plum Jam', 'Potato Chips',
//         //     'Potato Starch', 'Pudding Mix', 'Puff Pastry', 'Quince Jelly', 'Raspberry Jam',
//         //     'Red Pepper Jelly', 'Rice Flour', 'Rye Flour', 'Saltines', 'Self Rising Flour',
//         //     'Semolina', 'Shortcrust Pastry', 'Sorghum Flour', 'Sourdough Starter',
//         //     'Sponge Cake', 'Starch', 'Strawberry Jam', 'Stuffing Mix', 'Tapioca Flour',
//         //     'Tapioca Starch', 'Tortilla Chips', 'Vanilla', 'White Chocolate',
//         //     'Whole Wheat Flour', 'Yeast', 'Yeast Flake']);
//         // ingredients.set('Soups, Broth & Canned Goods',['Beef Broth', 'Canned Beets', 'Canned Carrots', 'Canned Corn',
//         //     'Canned Green Beans', 'Canned Peas', 'Canned Tomato', 'Canned Vegetables',
//         //     'Celery Soup', 'Chicken Broth', 'Chicken Soup', 'Dashi', 'Lamb Stock',
//         //     'Mushroom Soup', 'Onion Soup', 'Pork Stock', 'Tomato Soup', 'Veal Stock',
//         //     'Vegetable Bouillon', 'Vegetable Soup', 'Vegetable Stock']);
//         // return ingredients;
//     }
//
//     getPantry() {
//         let pantry = new Map();
//         for (let i = 0; i < types.length; i++) {
//             for (let j = 0; j < ingredients.get(types[i]).length; j++) {
//                 pantry.set(ingredients.get(types[i])[j], false);
//             }
//         }
//         return pantry;
//     }
//
//     getExpiration() {
//         let expiration = new Map();
//         for (let i = 0; i < types.length; i++) {
//             for (let j = 0; j < ingredients.get(types[i]).length; j++) {
//                 expiration.set(ingredients.get(types[i])[j], null);
//             }
//         }
//         return expiration;
//     }
//
//     async getPantryFromDB() {
//         let response = await fetch("http://localhost:4567/get-pantry?userId=0");
//         let dbPantry = await response.json();
//
//         let newPantry = this.state.pantry;
//         let newExpiration = this.state.expiration;
//
//         for (let i = 0; i < dbPantry.ingredients.length; i++) {
//             newPantry.set(dbPantry.ingredients[i].name, true);
//             newExpiration.set(dbPantry.ingredients[i].name, dbPantry.expirations[i]);
//         }
//
//         this.setState({
//             pantry: newPantry,
//             expiration: newExpiration
//         })
//     }
//
//     render() {
//         const { classes } = this.props;
//         return (
//             <div className={classes.root} onLoad={this.getPantryFromDB}>
//                 <Grid container spacing={3} justify="flex-end">
//                     <Grid item xs={12}>
//                         <Paper className={classes.header}>
//                             <Logo />
//                         </Paper>
//                     </Grid>
//                     <Grid item xs={5}>
//                         <Paper className={classes.pantry}>
//                             <div className={classes.pantryHeader}>
//                                 <Typography variant="h5" gutterBottom>
//                                     Pantry
//                                 </Typography>
//                                 <UpdatePantry types={this.state.types}
//                                               ingredients={this.state.ingredients}
//                                               pantry={this.state.pantry}
//                                               expiration={this.state.expiration}/>
//                             </div>
//                             <SimpleExpansionPanel types={this.state.types}
//                                                   ingredients={this.state.ingredients}
//                                                   pantry={this.state.pantry}
//                                                   expiration={this.state.expiration}/>
//                         </Paper>
//                     </Grid>
//                     <Grid item xs={7}>
//                         {/*<Paper className={classes.paper}>Filters</Paper>*/}
//                         <Paper className={classes.recipes}>
//                             <RecipeButton />
//                         </Paper>
//                     </Grid>
//                     <Grid item xs={12}> </Grid>
//                 </Grid>
//             </div>
//         );
//     }
// }
//
// export default withStyles(styles)(Layout);
// // export default Layout;
