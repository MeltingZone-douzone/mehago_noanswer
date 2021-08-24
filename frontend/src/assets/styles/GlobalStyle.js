import { createGlobalStyle } from "styled-components";
import reset from "styled-reset";

const GlobalStyle = createGlobalStyle`
    ${reset};
    body{
        width:100vw;
        max-width:100%;
        height:100%;
        padding: 0;
        margin: 0;
        font-family: 'Noto Sans KR', sans-serif;
    };
    button{
        display: flex;
        cursor: pointer;
        outline: none;
        border-radius: 3px;
    };
    input{
        display: flex;
        outline: none;
        padding-left: 10px;
    }

    .right {
        justify-content: flex-end !important;
      }
      .left {
        justify-content: flex-start !important;
      }
`;

export default GlobalStyle;
