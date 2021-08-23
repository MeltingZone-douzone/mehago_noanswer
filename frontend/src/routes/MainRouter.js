import React, { Fragment } from 'react';
import styled from 'styled-components';
import { Route, Switch, BrowserRouter as Router, Redirect } from 'react-router-dom';

import ProfileSettingsPage from './pages/ProfileSettingsPage';
import AccountPage from './pages/AccountPage';
import ChatPage from './pages/ChatPage';



export default function MainRouter() {

    return (
        <Router>
            <Fragment>
                {/* <Header /> */}
                <WebPage>
                    <Switch>
                        <Route exact path="/"><Redirect to="/account/login"/></Route>
                        <Route path="/profile"  component={ProfileSettingsPage}/>
                        <Route path="/account" component={AccountPage}/>
                        <Route path="/c" component={ChatPage}/>
                    </Switch>
                </WebPage>
            </Fragment>
        </Router>
    )
}

const WebPage = styled.div`
    padding:0 2em;
`