import { createTheme } from "@material-ui/core";
import { colors } from "../Colors";

export const theme = createTheme({
    palette: {
        primary : { main : colors.mainThemeColor },
        secondary : { main : colors.lightMainThemeColor },
    }
});