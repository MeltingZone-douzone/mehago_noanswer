import React from "react";
import makeStyles from "@material-ui/core/styles/makeStyles";

const useStyles = makeStyles(theme => ({
  container: {
    display: "flex",
    flexDirection:"column-reverse",
    
    height: "100%",
    bottom: 0
    
    // position: "fixed" // remove this so we can apply flex design
    
  },
  bubbleContainer: {
    width: "100%",
    display: "flex" //new added flex so we can put div at left and right side
    //check style.css for left and right classnaeme based on your data
  },
  bubble: {
    border: "0.5px solid black",
    borderRadius: "10px",
    padding: "10px",
    display: "inline-block"
  }
}));

const ChatLayout = () => {
  const classes = useStyles();
  const dummyData = [
    {
      message: "1: This should be in left",
      direction: "left"
    },
    {
      message: "2: This should be in right",
      direction: "right"
    },
    {
      message: "3: This should be in left again",
      direction: "left"
    },
    {
      message: "4: This should be in right again",
      direction: "right"
    }
  ];

  const chatBubbles = dummyData.map((obj, i = 0) => (
    <div className={`${classes.bubbleContainer} ${obj.direction}`} key={i}>
      <div key={i++} className={classes.bubble}>
        <div className={classes.button}>{obj.message}</div>
      </div>
      <span style={{textAlign:"center"}}>1</span>
    </div>
    
  ));

  
  return (
    <div className={classes.container}>
        {chatBubbles}
    </div>)
      ;
};

export default ChatLayout;
