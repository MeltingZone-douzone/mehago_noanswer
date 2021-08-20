import React from 'react';
import styled from 'styled-components';
import UserSettingsNavigation from './ProfileSettingsNavigation';
import UserSettingsTemplate from './ProfileSettingsTemplate';


export default function UserSettingsContainer({user}) {

    return(
        <SettingsContainer>
            <UserSettingsNavigation />
            <UserSettingsTemplate user = {user}/>
        </SettingsContainer>
    );
}

const SettingsContainer = styled.div`
    margin-top:2em;
    padding:0 2rem;
    width:70%;
`