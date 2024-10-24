// LoginScreen.js

import React, { useState, useContext } from 'react';
import { useNavigate } from "react-router-dom";
import { GlobalStateContext } from '../../GlobalStateContext';
import './LoginScreen.css'; // Importing the CSS file
import { Container, Form, Row, Col, Modal } from 'react-bootstrap';
import LoginServices from "../../services/LoginServices"
import { Button } from "../../components/ButtonElement";
import TM from "../../common/assets/form.svg";
import {
  HeroBtnWrapper,
  ArrowForward,
  ArrowRight,
} from "../../components/HeroSection/HeroElements"
import {
  FeaturesColumn,
  Img,
  FeaturesSec,
  ImgWrapper,
} from "../../components/DetaisSection/Features.elements";
export default function LoginComponent({ lightTopLine }) {
  const navigate = useNavigate();
  const [hover, setHover] = useState(false);
  const [state, dispatch] = useContext(GlobalStateContext);
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [otp, setOtp] = useState('')
  const [showOtpModal, setShowOtpModal] = useState(false);
  const onHover = () => {
    setHover(!hover);
  };

  const handleLogin = async (event) => {
    event.preventDefault()
    const response = await LoginServices.login(username, password).then(resp => {
      if (resp.data.message === 'Enter OTP generated' && resp.status === 200) {
        setShowOtpModal(true);
        dispatch({ type: 'SET_USER_DATA', payload: resp.data });
      }
    }).catch(err => {
      console.log(err)
    })

  }
  const handleOtpSubmit = async (event) => {
    event.preventDefault()
    // Logic to handle OTP submission
    console.log("sssss", state.userData.userId)
    const otpResponse = await LoginServices.verifyOtp(state.userData.userId, otp).then(
      resp => { 
        setShowOtpModal(false);
        const type=state.userData.typeOfUser.toLowerCase()
        if(type === "customer"){
          navigate("/dashboard");
        }
       
        
      }
    ).catch(err => {
      console.log(err)
    })

  };

  return (
    <>
      <FeaturesSec>
        <Container className="d-flex align-items-center justify-content-center " style={{ minHeight: "100vh" }}>

          <div className="w-100" style={{ maxWidth: "400px" }}>
            <Form>
              <h2 className="text-center mb-4">Login</h2>
              <Form.Group controlId="formBasicEmail">
                <Form.Label>Username</Form.Label>
                <Form.Control type="text" placeholder="Enter username"
                  value={username}
                  onChange={(e) => setUsername(e.target.value)} />
              </Form.Group>

              <Form.Group controlId="formBasicPassword">
                <Form.Label>Password</Form.Label>
                <Form.Control type="password" placeholder="Password"
                  value={password}
                  onChange={(e) => setPassword(e.target.value)} />
              </Form.Group>
              <HeroBtnWrapper style={{ width: "400px", display: "inline-block" }}>
                <Button
                  onClick={(event) => handleLogin(event)}
                  onMouseEnter={onHover}
                  onMouseLeave={onHover}
                  primary="true"
                  dark="true"
                >
                  Login {hover ? <ArrowForward /> : <ArrowRight />}
                </Button>
              </HeroBtnWrapper>


              <div className="w-100 text-center mt-3">
                <a href="/changepassword">Forgot Password?</a>
              </div>
            </Form>

            <Row className="py-4">
              <Col>
                <div className="w-100 text-center">
                  Need an account? <a href="/signup">Sign Up</a>
                </div>
              </Col>
            </Row>
          </div>
         
          <FeaturesColumn>
          <ImgWrapper style={{ position: "absolute" }}>
            <Img src={TM} alt="car" />
          </ImgWrapper>
        </FeaturesColumn>
        
          <Modal show={showOtpModal} onHide={() => setShowOtpModal(false)}>
            <Modal.Header closeButton>
              <Modal.Title>Enter OTP</Modal.Title>
            </Modal.Header>
            <Modal.Body>
              <Form>
                <Form.Group>
                  <Form.Label>OTP</Form.Label>
                  <Form.Control type="text" placeholder="4-digit OTP"
                    value={otp}
                    onChange={(e) => setOtp(e.target.value)} />
                </Form.Group>
                <HeroBtnWrapper style={{ width: "400px", display: "inline-block" }}>
                  <Button
                    onClick={(event) => handleOtpSubmit(event)}
                    onMouseEnter={onHover}
                    onMouseLeave={onHover}
                    primary="true"
                    dark="true"
                  >
                    Submit {hover ? <ArrowForward /> : <ArrowRight />}
                  </Button>
                </HeroBtnWrapper>
              </Form>
            </Modal.Body>
          </Modal>
         
        </Container>
      </FeaturesSec>
    </>
  );
};


