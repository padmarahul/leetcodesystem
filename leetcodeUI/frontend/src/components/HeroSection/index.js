import React, { useState } from "react";
// import { herosectionbg2 as Image } from "../../common/imageUrls";
import Image from "../../common/assets/bgImage.jpg";
import {
  HeroContainer,
  HeroBg,
  ImageBg,
  HeroContent,
  HeroH1,
  HeroP,
  HeroBtnWrapper,
  ArrowForward,
  ArrowRight,
} from "./HeroElements";
import { Button } from "../ButtonElement";
import Navbar2 from "../../components/Navbar/index";
const HeroSection = () => {
  const [hover, setHover] = useState(false);

  const onHover = () => {
    setHover(!hover);
  };
  return (
    <>
    <Navbar2 />
    <HeroContainer>
      <HeroBg>
        <ImageBg src={Image} />
      </HeroBg>
      <HeroContent>
        <HeroH1>LeetCode</HeroH1>
        <HeroP>
          <br />
          LeetCode is a popular online platform designed to help users improve their coding skills and prepare for technical interviews. It offers a vast collection of problems that span various topics, including algorithms, data structures, databases, and system design. LeetCode supports multiple programming languages such as Python, Java, C++, and JavaScript, allowing users to practice and solve problems in their preferred language. The platform also features mock interviews, coding contests, and a discussion forum where users can engage with a global community. LeetCode is widely used by software engineers and developers to enhance their problem-solving abilities and prepare for job interviews.
           </HeroP>
        <HeroBtnWrapper>
          <Button
            to="smartContract"
            onMouseEnter={onHover}
            onMouseLeave={onHover}
            primary="true"
            dark="true"
          >
            Get Started {hover ? <ArrowForward /> : <ArrowRight />}
          </Button>
        </HeroBtnWrapper>
      </HeroContent>
    </HeroContainer>
    </>
  );
};

export default HeroSection;
