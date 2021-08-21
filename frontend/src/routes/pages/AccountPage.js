import React from 'react';
import Logo from '../../assets/images/wehago.svg';
import styles from "../../assets/sass/account/AccountPage.scss";
import { Switch, Route, useLocation } from "react-router-dom";
import { AnimatePresence, motion } from "framer-motion";

import Links from "../../components/Links";
import LoginForm from "../../account/LoginForm";
import SignUpForm from "../../account/SignUpForm";
import PasswordSearchPage from "../../account/PasswordSearch";
import IdSearchPage from "../../account/IdSerach";

export default function AccountPage ({match}) {
    return (
        <div className = {styles.PageContainer}>
            <div className={styles.Page}>
                <div className={styles.LogoWrapper}>
                        <a href="#">
                            <img
                                src={Logo}
                                height="24"
                                alt="MEHAGO"
                            />
                        </a>
                    </div>
                <div className={styles.ContentWrapper}>
                
                        <AnimatePresence>
                            <Switch location={location} key={location.pathname}>
                                {/* <motion.div
                                  style={pageStyle}
                                  initial="initial"
                                  animate="in"
                                  exit="out"
                                  variants={pageVariants}
                                  transition={pageTransition}> */}
                                    <Route
                                      exact
                                      path={`${match.path}/login`}
                                      component={LoginForm}
                                    />
                                    <Route
                                      exact
                                      path={`${match.path}/signup`}
                                      component={SignUpForm}
                                    />
                                    <Route
                                      exact
                                      path={`${match.path}/idsearch`}
                                      component={IdSearchPage}
                                    />
                                    <Route
                                      exact
                                      path={`${match.path}/passwordsearch`}
                                      component={PasswordSearchPage}
                                    />
                                {/* </motion.div> */}
                            </Switch>
                        </AnimatePresence>
                </div>
                <Links />
            </div>
        </div>
    )
}

const pageVariants = {
    initial: {
        opacity: 0,
        scale: 0.8,
    },
    in: {
        opacity: 1,
        x: 0,
        scale: 1,
    },
    out: {
        opacity: 0,
        x: "100vw",
        scale: 0.8,
    },
};
  
const pageTransition = {
    type: "tween",
    ease: "linear",
    duration: 0.3
};
  
const pageStyle = {
    position: "absolute",
};