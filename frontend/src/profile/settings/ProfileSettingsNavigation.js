import React from 'react';
import styled from 'styled-components';
import { NavLink } from 'react-router-dom';
import { colors } from '../../assets/styles/properties/Colors';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faBell, faUser } from '@fortawesome/free-solid-svg-icons';


export default function UserSettingsNavigation({match}) {

    const activeStyle = {
        color: colors.mainThemeColor,
        borderColor: colors.mainThemeColor,
    };

    return(
        <SettingsNavContainer>
            <NavItemGroup to={`${match.path}/info`} activeStyle={activeStyle}><FontAwesomeIcon icon={faUser}/><p>Profile</p></NavItemGroup>
            <NavItemGroup to={`${match.path}/alram`} activeStyle={activeStyle}><FontAwesomeIcon icon={faBell}/><p>Alarm</p></NavItemGroup>
        </SettingsNavContainer>
    );
}

const SettingsNavContainer = styled.div`
    width:100%;
    height:3em;
    display:flex;
    place-content:center;
`

const NavItemGroup = styled(NavLink)`
    width:4em;
    height:95%;
    padding:0 .5em;
    display:flex;
    justify-content:center;
    align-items:center;
    text-decoration: none;
    border-bottom: 2px solid #ccc;
    
    color: #ccc;

    & + &{
        margin-left: 20px;
    }

    p {
        margin-left:5px;
    }
`