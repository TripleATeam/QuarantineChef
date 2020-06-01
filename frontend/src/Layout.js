import React from 'react';

import { makeStyles } from '@material-ui/core/styles';
import Grid from '@material-ui/core/Grid';
import Paper from '@material-ui/core/Paper';
import Typography from '@material-ui/core/Typography';
import * as d3 from 'd3';

import IngredientTypeExpansionPanel from './Pantry';
import RecipeButton from "./FindRecipes";
import FilterPanel from "./Filters";
import UpdateDBPantry from "./UpdateDBPantry";
import MenuBar from "./MenuBar";
import Button from "@material-ui/core/Button";

const ingredients = new Map();

const typeMap = new Map();
typeMap.set("Poultry", "Poultry & Eggs");
typeMap.set("Seafood", "Fish & Seafood");
typeMap.set("Meat", "Red Meat");
typeMap.set("Vegetables", "Vegetables & Herbs");
typeMap.set("Fruit", "Fruits & Berries");
typeMap.set("Dairy", "Dairy & Dairy Alternatives");
typeMap.set("Grains", "Grains, Breads, Pasta & Cereal");
typeMap.set("Spices", "Spices & Seasonings");
typeMap.set("Condiments", "Condiments, Oils & Sauces");
typeMap.set("Sweeteners", "Sweeteners");
typeMap.set("Nuts", "Nuts, Seeds & Legumes");
typeMap.set("Beverages", "Beverages & Alcohol");
typeMap.set("Baking", "Baking, Sweets & Snacks");
typeMap.set("Soups", "Soups, Broth & Canned Goods");

const types = Array.from(typeMap.values());

for (let i in types) {
    ingredients.set(types[i], []);
}

let ingredients_csv = require("./ingredients.csv");

function extractIngredients() {
    d3.csv(ingredients_csv).then(function(data) {
        for (let i = 0; i < data.length; i++) {
            ingredients.get(typeMap.get(data[i].Type)).push(data[i].Ingredient);
        }
    });
}

// maps for pantry & expiration to push data to database
const pantry = new Map();
const expiration = new Map();

// initialize pantry & expiration dates with false/null values
// i.e. user does not have any ingredients and has not set
// any expiration dates for those ingredients
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
    },
    header: {
        padding: theme.spacing(2),
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

// function to get user ingredients and expiration dates from database to populate virtual pantry
async function getPantryFromDatabase() {

    let response = await fetch("https://backend-dot-quarantine-chef-278622.wl.r.appspot.com/get-pantry",{credentials: 'include'});
    let databasePantry = await response.json();

    if (databasePantry !== null) {
        for (let i = 0; i < databasePantry.ingredients.length; i++) {
            pantry.set(databasePantry.ingredients[i].name, true);
            if (databasePantry.expirations[i] !== "null") {
                const parts = databasePantry.expirations[i].split("-");
                const date = new Date(+parts[2], +parts[1] - 1, +parts[0]);
                expiration.set(databasePantry.ingredients[i].name, date);
            }
        }
    }
}

// This function returns the overall layout for the web app
export default function Layout() {
    // use styles for this functional component
    const classes = useStyles();

    // parse ingredients.csv file
    extractIngredients();

    let filterData = {};

    const [isLoggedIn, logIn] = React.useState(false);

    const handleFilterData = (data) => {
        filterData = data;
    }

    const getFilterData = () => {
        return filterData;
    }

    const handleUserUpdate = async () => {
        await getPantryFromDatabase();
        logIn(true);
    }

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
