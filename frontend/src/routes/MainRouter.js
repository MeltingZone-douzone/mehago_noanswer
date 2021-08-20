import React, { Fragment } from 'react';
import styled from 'styled-components';
import { Route, Switch, BrowserRouter as Router } from 'react-router-dom';

import ProfileSettingsPage from './pages/ProfileSettingsPage';
import AccountPage from './pages/AccountPage';



export default function MainRouter() {

    return (
        <Router>
            <Fragment>
                {/* <Header /> */}
                <WebPage>
                    <Switch>
                        <Route exact path="/"  component={AccountPage}/>
                        <Route path="/profile"  component={ProfileSettingsPage}/>
                        <Route path="/account" component={AccountPage}/>
                    </Switch>
                </WebPage>
            </Fragment>
        </Router>
    )
}

const WebPage = styled.div`
    padding:0 2em;
`