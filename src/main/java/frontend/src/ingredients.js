import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import ListItemSecondaryAction from '@material-ui/core/ListItemSecondaryAction';
import ListItemText from '@material-ui/core/ListItemText';
import Checkbox from '@material-ui/core/Checkbox';
// import IconButton from '@material-ui/core/IconButton';
// import CommentIcon from '@material-ui/icons/Comment';
import MaterialUIPickers from './expiration.js';

const useStyles = makeStyles((theme) => ({
    root: {
        width: '100%',
        maxWidth: 500,
        // backgroundColor: theme.palette.background.paper,
    },
}));

function enumerate(list) {
  let nums = [];
  for (let i = 0; i < list.length; i++) {
      nums.push(i);
  }
  return nums;
}

const ingredients = new Map();
ingredients.set('Dairy', ['Milk', 'Butter', 'Cheese']);
ingredients.set('Meat', ['Beef', 'Pork', 'Lamb']);
ingredients.set('Grains', ['Rice', 'Oats', 'Quinoa']);
ingredients.set('Vegetables', ['Carrots', 'Broccoli', 'Onions']);

export default function CheckboxList() {
    const classes = useStyles();
    const [checked, setChecked] = React.useState([0]);

    const handleToggle = (value) => () => {
        const currentIndex = checked.indexOf(value);
        const newChecked = [...checked];

        if (currentIndex === -1) {
            newChecked.push(value);
        } else {
            newChecked.splice(currentIndex, 1);
        }

        setChecked(newChecked);
    };

    return (
        <List className={classes.root}>
            {enumerate(ingredients.get('Dairy')).map((value) => {
                const labelId = `checkbox-list-label-${value}`;

                return (
                    <ListItem key={value} role={undefined} dense button onClick={handleToggle(value)}>
                        <ListItemIcon>
                            <Checkbox
                                edge="start"
                                checked={checked.indexOf(value) !== -1}
                                // tabIndex={-1}
                                disableRipple
                                inputProps={{ 'aria-labelledby': ingredients.get('Dairy')[value] }}
                            />
                        </ListItemIcon>
                        <ListItemText id={labelId} primary={ingredients.get('Dairy')[value]} />
                        <ListItemSecondaryAction>
                            <MaterialUIPickers />
                            {/*<IconButton edge="end" aria-label="comments">*/}
                            {/*    <CommentIcon />*/}
                            {/*</IconButton>*/}
                        </ListItemSecondaryAction>
                    </ListItem>
                );
            })}
        </List>
    );
}
