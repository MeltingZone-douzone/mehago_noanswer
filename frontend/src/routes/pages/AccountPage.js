<<<<<<< HEAD
import React, { useEffect } from "react";
import { Redirect, Route } from 'react-router-dom';
import { Switch, useLocation } from "react-router-dom";
=======
import React from "react";
import { Switch, Route, useLocation } from "react-router-dom";
>>>>>>> origin/sewon
import { AnimatePresence, motion } from "framer-motion";

import Links from "../../components/Links";
import LoginForm from "../../account/LoginForm";
import SignUpForm from "../../account/SignUpForm";
import PasswordSearchPage from "../../account/PasswordSearch";
import IdSearchPage from "../../account/IdSerach";
import styles from "../../assets/sass/LoginPage.scss";

export default function AccountPage({ match }) {
  const location = useLocation();
  return (
<<<<<<< HEAD
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
=======
    <div className={styles.PageWrapper}>
      <div className={styles.Page}>
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
>>>>>>> origin/sewon
              </div>
              <AnimatePresence>
                <Switch location={location} key={location.pathname}>
                  <motion.div
                    style={pageStyle}
                    initial="initial"
                    animate="in"
                    exit="out"
                    variants={pageVariants}
                    transition={pageTransition}
                  >
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
                  </motion.div>
                </Switch>
              </AnimatePresence>
            </div>
          </div>
        </div>
        <Links />
      </div>
    </div>
  );
}

const pageVariants = {
  initial: {
    opacity: 0,
    x: "-100vw",
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
  duration: 0.3,
};

const pageStyle = {
  position: "absolute",
};
