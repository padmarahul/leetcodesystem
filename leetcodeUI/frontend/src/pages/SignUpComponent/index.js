import React, { useState } from 'react';
import { useNavigate } from "react-router-dom";
import LoginServices from "../../services/LoginServices"
import CircularProgress from '@mui/material/CircularProgress';
import Box from '@mui/material/Box';
import '../../components/DetaisSection/Features.css'
import { Button } from "../../components/ButtonElement";
import {
  HeroBtnWrapper,
  ArrowForward,
  ArrowRight,
} from "../../components/HeroSection/HeroElements"
import {
  FeaturesRow,
  FeaturesColumn,
  TextWrapper,
  TopLine,
  FeaturesSec,
  FeaturesContainer,
  Table
} from "../../components/DetaisSection/Features.elements";
const SignUpComponent = ({ lightTopLine }) => {
  const navigate = useNavigate();
  const [hover, setHover] = useState(false);
  const [loading, setLoading] = useState(false)
  const [user, setUser] = useState({
    userName: '',
    password: '',
    firstName: '',
    lastName: '',
    emailId: '',
    gender: '',
    mobileNumber: '',
    zipcode: '',
    typeOfUser: ''
  });

  const onHover = () => {
    setHover(!hover);
  };
  const handleChange = (e) => {
    setUser({ ...user, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    LoginServices.signUp(user).then(response => {
      console.log(response.data)
      setLoading(e => !e)
      navigate('/login')
    }).catch(error => {
      console.log(error)
    })
  };

  const renderInputField = (key, value) => {
    switch (key) {
      case 'typeOfUser':
        return (
          <select name={key} value={value} onChange={handleChange}
            style={{
              borderRadius: "6px",
              textAlign: "center",
              padding: "0.7vh",
              marginLeft: "1vh",
            }}>
            <option value="">Select User Type</option>
            <option value="CUSTOMER">Customer</option>
          </select>
        );
      case 'gender':
        return (
          <select name={key} value={value} onChange={handleChange}
            style={{
              borderRadius: "6px",
              textAlign: "center",
              padding: "0.7vh",
              marginLeft: "1vh",
            }}>
            <option value="">Select Gender</option>
            <option value="Male">Male</option>
            <option value="Female">Female</option>
            <option value="Other">Other</option>
          </select>
        );
      case 'password':
        return (
          <input
            style={{
              borderRadius: "6px",
              textAlign: "center",
              padding: "0.7vh",
              marginLeft: "1vh",
            }}
            type="password"
            name={key}
            value={value}
            onChange={handleChange}
          />
        );
      case 'mobileNumber':
        return (
          <input
            style={{
              borderRadius: "6px",
              textAlign: "center",
              padding: "0.7vh",
              marginLeft: "1vh",
            }}
            type="number"
            name={key}
            value={value}
            onChange={handleChange}
          />
        );
      case 'zipcode':
        return (
          <input
            style={{
              borderRadius: "6px",
              textAlign: "center",
              padding: "0.7vh",
              marginLeft: "1vh",
            }}
            type="number"
            name={key}
            value={value}
            onChange={handleChange}
          />
        );
      default:
        return (
          <input
            style={{
              borderRadius: "6px",
              textAlign: "center",
              padding: "0.7vh",
              marginLeft: "1vh",
            }}
            type="text"
            name={key}
            value={value}
            onChange={handleChange}
          />
        );
    }
  };

  return (
    <>
      <FeaturesSec>
        {loading ? (
          <div
            style={{
              margin: "auto",
              top: "50%",
              display: "block",
            }}
          >
            <Box sx={{ display: "flex" }}>
              <CircularProgress />
            </Box>
          </div>
        ) : (
          <FeaturesContainer>
            <FeaturesRow>
              <TopLine lightTopLine={lightTopLine}>
                PART PRO WEBSITE
                <p style={{ color: "grey", fontSize: "2vh", marginTop: "1vh" }}>
                  Registration Details
                </p>
              </TopLine>
              <FeaturesColumn>
                <TextWrapper>
                  <Table>
                    <tbody>
                      {Object.entries(user).map(([key, value]) => (
                        <tr key={key}>
                          <td style={{ textAlign: 'left', paddingRight: '10px' }}>
                            {key}
                          </td>
                          <td style={{
                            color: "white",
                            padding: "1vh",
                            fontSize: "2vh",
                          }}>
                            {renderInputField(key, value)}
                          </td>
                        </tr>
                      ))}
                    </tbody>
                  </Table>
                </TextWrapper>
                <HeroBtnWrapper style={{ display: "inline-block" }}>
                  <Button
                    onClick={(event) => handleSubmit(event)}
                    onMouseEnter={onHover}
                    onMouseLeave={onHover}
                    primary="true"
                    dark="true"
                  >
                    Create User {hover ? <ArrowForward /> : <ArrowRight />}
                  </Button>
                </HeroBtnWrapper>
              </FeaturesColumn>
            </FeaturesRow>
          </FeaturesContainer>
        )}
      </FeaturesSec>
    </>
  );

};

export default SignUpComponent;
