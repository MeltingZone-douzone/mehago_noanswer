import React from 'react';
import { Route } from 'react-router-dom';
import styled from 'styled-components';
import AlramSettingsTemplate from './AlramSettingsTemplate';
import UserSettingsNavigation from './ProfileSettingsNavigation';
import UserSettingsTemplate from './ProfileSettingsTemplate';


export default function UserSettingsContainer({user, match}) {

    return(
        <SettingsContainer>
            <UserSettingsNavigation match={match} />
            <Route path={"/profile/info"} render={props => <UserSettingsTemplate {...props}/>} />
            <Route path={"/profile/alram"} component={AlramSettingsTemplate(user)} />
        </SettingsContainer>
    );
}

const SettingsContainer = styled.div`
    margin-top:2em;
    padding:0 2rem;
    width:70%;
`