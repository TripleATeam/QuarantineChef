import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import ExpansionPanel from '@material-ui/core/ExpansionPanel';
import ExpansionPanelSummary from '@material-ui/core/ExpansionPanelSummary';
import ExpansionPanelDetails from '@material-ui/core/ExpansionPanelDetails';
import Typography from '@material-ui/core/Typography';
import ExpandMoreIcon from '@material-ui/icons/ExpandMore';
import CheckboxList from './ingredients';

const useStyles = makeStyles((theme) => ({
    root: {
        width: '100%',
        maxWidth: 500,
    },
    heading: {
        fontSize: theme.typography.pxToRem(15),
        fontWeight: theme.typography.fontWeightRegular,
    },
}));

function enumerate(list) {
    let nums = [];
    for (let i = 0; i < list.length; i++) {
        nums.push(i);
    }
    return nums;
}

const types = ['Poultry & Eggs','Fish & Seafood','Red Meat','Vegetables & Herbs','Fruits',
               'Dairy & Dairy Alternatives', 'Grains, Breads & Cereals','Spices',
               'Condiments','Sweeteners','Nuts, Seeds & Legumes','Alcohol & Beverages',
               'Baking & Sweets','Snack Foods','Soups & Broth'];

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
                'Cilantro', 'Collard', 'Corn', 'Cress', 'Cucumber', 'Daikon', 'Dill', 'Dulse',
                'Eggplant', 'Endive', 'Fennel', 'Frozen Vegetables', 'Garlic', 'Ginger',
                'Green Beans', 'Hearts Of Palm', 'Horseradish', 'Jerusalem Artichoke', 'Jicama',
                'Kale', 'Kohlrabi', 'Leek', 'Micro Greens', 'Mint', 'Mixed Vegetable', 'Mushroom',
                'Mustard Greens', 'Okra', 'Olive', 'Onion', 'Parsley', 'Parsnip', 'Pickle',
                'Pimiento', 'Porcini', 'Portobello Mushroom', 'Potato', 'Pumpkin', 'Radicchio',
                'Radish', 'Red Onion', 'Rocket', 'Rosemary', 'Rutabaga', 'Salad Greens',
                'Sauerkraut', 'Scallion', 'Seaweed', 'Shallot', 'Snow Peas', 'Spaghetti Squash',
                'Spinach', 'Squash', 'Sweet Pepper', 'Sweet Potato', 'Turnip', 'Water Chestnut',
                'Watercress', 'Yam', 'Zucchini']);
ingredients.set('Fruits',['Apple', 'Apple Butter', 'Apricot', 'Avocado', 'Banana', 'Berries',
                'Blackberry', 'Blueberry', 'Boysenberry', 'Cantaloupe', 'Cherry', 'Clementine',
                'Coconut', 'Crabapples', 'Craisins', 'Cranberry', 'Currant', 'Date', 'Fig',
                'Grape', 'Grapefruit', 'Guava', 'Honeydew', 'Kiwi', 'Kumquat', 'Lemon', 'Lime',
                'Lingonberry', 'Lychee', 'Mandarin', 'Mango', 'Nectarine', 'Orange', 'Papaya',
                'Passion Fruit', 'Peach', 'Pear', 'Persimmons', 'Pineapple', 'Plantain', 'Plum',
                'Pomegranate', 'Prunes', 'Quince', 'Raisin', 'Raspberry', 'Rhubarb', 'Star Fruit',
                'Strawberry', 'Sultanas', 'Sun Dried Tomato', 'Tangelos', 'Tangerine', 'Tomatillo',
                'Tomato', 'Watermelon']);
ingredients.set('Dairy & Dairy Alternatives',[]);
ingredients.set('Grains, Breads & Cereals',[]);
ingredients.set('Spices',[]);
ingredients.set('Condiments',[]);
ingredients.set('Sweeteners',[]);
ingredients.set('Nuts, Seeds & Legumes',[]);
ingredients.set('Alcohol & Beverages',[]);
ingredients.set('Baking & Sweets',[]);
ingredients.set('Snack Foods',[]);
ingredients.set('Soups & Broth',[]);

export default function SimpleExpansionPanel() {
    const classes = useStyles();

    return (
        <div className={classes.root}>
            {enumerate(types).map((value) => {
                const labelId = `checkbox-list-label-${value}`;
                return (
                    <ExpansionPanel>
                        <ExpansionPanelSummary
                            expandIcon={<ExpandMoreIcon />}
                            aria-controls={`${labelId}`}
                            id={`${labelId}`}
                        >
                            <Typography className={classes.heading}>{types[value]}</Typography>
                        </ExpansionPanelSummary>
                        <ExpansionPanelDetails>
                            <CheckboxList type={types[value]}
                                          ingredients={ingredients.get(types[value])} />
                        </ExpansionPanelDetails>
                    </ExpansionPanel>
                );
            })}
        </div>
    );
}
