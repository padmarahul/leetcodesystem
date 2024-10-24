import React, { useState, useContext } from 'react';
import { useNavigate } from "react-router-dom";
import CustomerServices from "../../services/CustomerServices"
import { GlobalStateContext } from '../../GlobalStateContext';
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

const UpdatePersonalDetails = ({ lightTopLine }) => {
    const [hover, setHover] = useState(false);
    const navigate = useNavigate();
    const [loading, setLoading] = useState(false)
    const [state] = useContext(GlobalStateContext);
    const [customer, setCustomer] = useState({
        fullName: '',
        emailId: '',
        mobileNumber: '',
        zipcode: '',
    });
    const onHover = () => {
        setHover(!hover);
    };
    const renderInputField = (key, value) => {
        switch (key) {
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
    const handleChange = (e) => {
        setCustomer({ ...customer, [e.target.name]: e.target.value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        console.log(customer);
        CustomerServices.updatePersonalDetails(state.userData.userId, customer).then(response => {
            console.log(response.data)
            setLoading(e => !e)
            navigate('/dashboard')
        }).catch(error => {
            console.log(error)
        })
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
                                    Update Personal Details
                                </p>
                            </TopLine>
                            <FeaturesColumn>
                                <TextWrapper>
                                    <Table>
                                        <tbody>
                                            {Object.entries(customer).map(([key, value]) => (
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
                                        Update Personal Details {hover ? <ArrowForward /> : <ArrowRight />}
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

export default UpdatePersonalDetails;
