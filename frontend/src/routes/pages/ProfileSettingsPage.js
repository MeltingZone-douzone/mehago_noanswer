import React, { useEffect, useState } from 'react';
import styled from 'styled-components';
import UserProfile from '../../profile/UserProfile';
import UserSettingsContainer from '../../profile/settings/ProfileSettings';


import { getUserInfo } from '../../../api/AccountApi';
export default function ProfileSettingsPage({match}) {

    const [userInfo, setUserInfo] = useState({nickname:"noNickname", name:"hong", telephoneNum:"01022221234" , thumbnailUrl:"" });

    useEffect(() =>{
        // Todo 유저 정보가져오기.
        console.log(getUserInfo());
    },[])


    return(
        <Template>
            <UserProfile user={userInfo} />
            <UserSettingsContainer user={userInfo} match={match}/>
        </Template>
    );
}

const Template = styled.div`
    display:flex;
`