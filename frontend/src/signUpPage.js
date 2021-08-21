import React from "react";
import SignUpForm from "./signUp/SignUpForm";
import styles from "./assets/scss/SignUpPage.scss";

export default function signUpPage() {
  return (
    <div className={styles.MainPageWrapper}>
      <div className={styles.InitialView}>
        <div className={styles.ContentWrapper}>
          <div className={styles.Content}>
            <div className={styles.LogoWrapper}>
              <div className={styles.Logo}>
                <a href="#">
                  <img
                    src="./assets/image/wehago.svg"
                    height="24"
                    alt="MEHAGO"
                  />
                </a>
              </div>
            </div>
            <SignUpForm/>
          </div>
        </div>
      </div>
    </div>
  );
}
