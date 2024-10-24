
import React, { useState } from 'react';
import { useNavigate } from "react-router-dom";
import { Container, Form} from 'react-bootstrap';
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
  ImgWrapper
} from "../../components/DetaisSection/Features.elements";

export default function ForgotPassword({ lightTopLine }) {
  const navigate = useNavigate();
  const [hover, setHover] = useState(false);
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const onHover = () => {
    setHover(!hover);
  };

  const handleChangePassword= async (event) => {
    event.preventDefault()
    const response = await LoginServices.changepassword(username, password).then(resp => {
      if (resp.status === 200) {
       
        navigate('/login');
      }
    }).catch(err => {
      console.log(err)
    })

  }
 
  return (
    <>
      <FeaturesSec>
        <Container className="d-flex align-items-center justify-content-center " style={{ minHeight: "100vh" }}>

          <div className="w-100" style={{ maxWidth: "400px" }}>
            <Form>
              <h2 className="text-center mb-4">Change Password </h2>
              <Form.Group controlId="formBasicEmail">
                <Form.Label>Username</Form.Label>
                <Form.Control type="text" placeholder="Enter username"
                  value={username}
                  onChange={(e) => setUsername(e.target.value)} />
              </Form.Group>

              <Form.Group controlId="formBasicPassword">
                <Form.Label>New Password</Form.Label>
                <Form.Control type="password" placeholder="Password"
                  value={password}
                  onChange={(e) => setPassword(e.target.value)} />
              </Form.Group>
              <HeroBtnWrapper style={{ width: "400px", display: "inline-block" }}>
                <Button
                  onClick={(event) => handleChangePassword(event)}
                  onMouseEnter={onHover}
                  onMouseLeave={onHover}
                  primary="true"
                  dark="true"
                >
                  Change Password {hover ? <ArrowForward /> : <ArrowRight />}
                </Button>
              </HeroBtnWrapper>
            </Form>
          </div>
          <FeaturesColumn>
          <ImgWrapper style={{ position: "absolute" }}>
            <Img src={TM} alt="car" />
          </ImgWrapper>
        </FeaturesColumn>
        </Container>
      </FeaturesSec>
    </>
  );
};


