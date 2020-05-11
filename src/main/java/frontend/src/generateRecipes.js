import React, { Component } from "react";
import TextField from "@material-ui/core/TextField";
import FormControl from "@material-ui/core/FormControl";
import Container from "@material-ui/core/Container";
import Button from "@material-ui/core/Button";
import GridList from "@material-ui/core/GridList";
import GridListTile from "@material-ui/core/GridListTile";
import GridListTileBar from "@material-ui/core/GridListTileBar";

class RecipeComponent extends Component {
  constructor(props) {
    super(props);
    this.state = { value: "", recipes: [] };
    this.handleChange = this.handleChange.bind(this);
    this.handleSearch = this.handleSearch.bind(this);
  }

  async handleSearch(event) {
    let response = await fetch("http://localhost:4567/find-recipe?key-ingredient=" + this.state.value);
    let recipes = await response.json();
    this.setState({
      recipes: recipes,
    });
    event.preventDefault();
  }

  async handleTest(event) {
    let pantry = {
      ingredients: ["Beef Steak", "Chicken Breast", "Eggs"],
      expirations: ["01-06-2020", "02-01-2020", "03-06-2020"],
      quantities: [1, 1, 1],
    };
    const requestOptions = {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(pantry),
    };

    fetch("http://localhost:4567/save-pantry", requestOptions);

    event.preventDefault();
  }

  handleChange(event) {
    this.setState({ value: event.target.value });
  }

  render() {
    return (
      <Container maxWidth="sm">
        <FormControl fullWidth variant="outlined">
          <TextField label="Enter Key Ingredient" value={this.state.value} onChange={this.handleChange} />
        </FormControl>
        <FormControl>
          <Button variant="contained" onClick={this.handleSearch}>
            Search Recipe
          </Button>
          <Button variant="contained" onClick={this.handleTest}>
            Test
          </Button>
        </FormControl>
        <div>
          <GridList cellHeight={160} cols={2}>
            {this.state.recipes.map((recipe) => (
              <GridListTile key={recipe.Img} component="a" href={recipe.Url} target="_blank">
                <img src={recipe.Img} alt={recipe.Name} />
                <GridListTileBar title={recipe.Name} />
              </GridListTile>
            ))}
          </GridList>
        </div>
      </Container>
    );
  }
}

export default RecipeComponent;
