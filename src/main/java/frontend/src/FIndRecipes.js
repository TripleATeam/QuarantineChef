import React, { Component } from "react";
import FormControl from "@material-ui/core/FormControl";
import Container from "@material-ui/core/Container";
import Button from "@material-ui/core/Button";
import GridList from "@material-ui/core/GridList";
import GridListTile from "@material-ui/core/GridListTile";
import GridListTileBar from "@material-ui/core/GridListTileBar";

// this component handles recipe search/generation by communicating with
// the backend recipe search methods
class RecipeComponent extends Component {
  constructor(props) {
    super(props);
    this.state = { value: "", recipes: [] };
    // this.handleChange = this.handleChange.bind(this);
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

  // async componentDidMount() {
  //   let response = await fetch("http://localhost:4567/get-pantry?userId=0");
  //   let pantry = await response.json();
  // }

  // handleChange(event) {
  //   this.setState({ value: event.target.value });
  // }

  render() {
    return (
      <Container maxWidth="sm">
        <FormControl>
          <Button variant="contained"
                  color="primary"
                  onClick={this.handleSearch}>
            Find Recipes
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
