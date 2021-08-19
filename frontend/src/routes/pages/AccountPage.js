import React, { useEffect } from "react";
import { Redirect, Route } from 'react-router-dom';

import Links from "../../components/Links";
import NonMembers from "../../components/NonMember";
import LoginForm from "../../account/LoginForm";
import SignUpForm from "../../account/SignUpForm";
import PasswordSearchPage from "../../account/PasswordSearch";
import IdSearchPage from "../../account/IdSerach";
import styles from "../../assets/sass/LoginPage.scss";

export default function AccountPage({match}) {
   


    return (
      <div className={styles.MainPageWrapper}>
        <div className={styles.InitialView}>
          <div className={styles.ContentWrapper}>
            <div className={styles.Content}>
              <div className={styles.LogoWrapper}>
                <div className={styles.Logo}>
                  <a href="#">
                    <img
                      src="../../assets/images/wehago.svg"
                      height="24"
                      alt="MEHAGO"
                    />
                  </a>
                </div>
              </div>
                  <Route exact path={`${match.path}/login`} component={LoginForm}/>
                  <Route exact path={`${match.path}/signup`} component={SignUpForm} />
                  <Route exact path={`${match.path}/idsearch`} component={IdSearchPage} />
                  <Route exact path={`${match.path}/passwordsearch`} component={PasswordSearchPage} />
              <NonMembers />
              <Links />
            </div>
          </div>
        </div>
      </div>
    );
}