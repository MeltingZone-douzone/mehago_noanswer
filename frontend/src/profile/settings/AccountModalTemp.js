import React from 'react';
import styled from 'styled-components';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faTimes } from '@fortawesome/free-solid-svg-icons';
import { colors } from '../../assets/styles/properties/Colors';

import NicknameModal from './NicknameModal';
import PasswordModal from './PasswordModal';
import ThumbnailModal from './ThumbnailModal';
const AccountModalTemp = ({title,content,onClose}) =>{
    
    function getComponent(){
        switch(content){
            case 'thumbnail' : return(<ThumbnailModal onClose={onClose}/>);
                break;
            case 'nickname' : return(<NicknameModal onClose={onClose}/>);
                break;
            case 'password' : return(<PasswordModal onClose={onClose}/>);
                break;
        }
    }

    return(
        <Overlay onClick={ onClose}>
            <ModalContainer onClick={(e)=>e.stopPropagation()}>
                <TitleDiv>{title}<Cancel onClick={onClose}>
                    <FontAwesomeIcon icon={faTimes} size={'sm'} color={colors.mainThemeColor}/>
                    </Cancel></TitleDiv>
                <Content>
                    {getComponent()}
                </Content>
            </ModalContainer>
        </Overlay>
    );
}

export default AccountModalTemp;

const Overlay = styled.div`
    position: fixed;
    z-index:2;
    
    width:100vw;
    height:100vh;
    top: 0;
    left: 0;
    background-color: rgba(0,0,0,.45);
`

const ModalContainer = styled.div`
    position:absolute;
    top:0;
    left:0;
    width:50%;
    max-width:600px;
    height:auto;
    
    left: 50%;
    top: 50%;
    transform: translate(-50%, -50%);

    background: #fff;
    border: 1px solid #ccc;
    border-color: rgba(0,0,0,.2);
    box-shadow: 0px 4px 10px rgba(0,0,0,.2);
    color:#000;
    border-radius: 8px;

    @media only screen and (max-width: 1300px){
        width:70%;
    }

    @media only screen and (max-width: 800px){
        height:100%;
        width:100%;
    }
`


const TitleDiv = styled.div`
    display:flex;
    padding:1em;
    font-size:1.2rem;

    border-bottom:1px solid #ccc;
    border-color: rgba(0,0,0,.2);
    box-shadow: 0px 4px 10px rgba(0,0,0,.2);
`
const Cancel = styled.div`
    margin-left:auto;
`

const Content = styled.div`
    border-bottom:1px solid #ccc;
    border-color: rgba(0,0,0,.2);
`