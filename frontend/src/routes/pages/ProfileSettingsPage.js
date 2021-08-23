import React, { useEffect, useState } from 'react';
import styled from 'styled-components';
import UserProfile from '../../profile/UserProfile';
import UserSettingsContainer from '../../profile/settings/ProfileSettings';


import { getUserInfo } from '../../../api/AccountApi';
export default function ProfileSettingsPage({ match }) {

    const [userInfo, setUserInfo] = useState({ nickname: "", name: "", phoneNumber: "", thumbnailUrl: "" });

    useEffect(() => {
        getUserInfo().then(res => setUserInfo(res.data));
    }, [])


    return (
        <Template>
            <UserProfile user={userInfo} />
            {/* <UserSettingsContainer user={userInfo} match={match} /> */}
        </Template>
    );
}

const Template = styled.div`
    display:flex;
`