import React from 'react';
import styled from 'styled-components';
import { NavLink } from 'react-router-dom';
import { colors } from '../../assets/styles/Colors';
export default function UserSettingsNavigation() {

    const activeStyle = {
        color: '#ffca08',
        borderColor: '#ffca08',
        borderBottom: '5px solid #ffca08',
        boxShadow: '0px 4px 10px rgba(0,0,0,.2)'
    };

    return(
        <SettingsNavContainer>
            {/* <NavItemGroup to={"/"} activeStyle={activeStyle}><Icon icon={faUser}/><p>Profile</p></NavItemGroup>
            <NavItemGroup to={"/"} activeStyle={activeStyle}><Icon icon={faBell}/><p>Alarm</p></NavItemGroup> */}
            <NavItemGroup ><p>Profile</p></NavItemGroup>
            <NavItemGroup ><p>Alarm</p></NavItemGroup>
        </SettingsNavContainer>
    );
}

const SettingsNavContainer = styled.div`
    width:100%;
    height:3em;
    display:flex;
    place-content:center;
`

const NavItemGroup = styled.div`
    width:4em;
    height:95%;
    padding:0 .5em;
    display:flex;
    justify-content:center;
    align-items:center;
    
    border-bottom: 2px solid ${colors.mainThemeColor};
    
    color: ${colors.mainThemeColor};

    & + &{
        margin-left: 20px;
    }
`