import { useFormik } from "formik";
import {CoverLetterModel} from "../../../types/models/CoverLetterModel.model";
import {useNavigate} from "react-router-dom";
import CoverLetterForm from "../../organisms/CoverLetterForm";
import GeneralBox from "../../molecules/GeneralBox";
import CoverLetterService from "../../../Services/CoverLetterService";



export const initialCoverLetterValues: any = {
    id: '',
    name: '',
    surname: '',
    age: '',
    appliedCompany: '',
    position: '',
    currentEducationLevel: '',
    skills: '',
    interests: '',
    weaknesses: '',
};

const CoverLetter = () => {
    const navigate = useNavigate();
    const formik = useFormik({
        initialValues: initialCoverLetterValues,
        onSubmit: (values) => {
            const coverLetter = values as CoverLetterModel;
            coverLetter.skills = values.skills.split(',');
            coverLetter.weaknesses =values.weaknesses.split(',');
            coverLetter.interests = values.interests.split(',');
            CoverLetterService.addCoverLetter(coverLetter).then((res) => {
                const encodedData = encodeURIComponent(JSON.stringify(res));
                navigate(`/generatedCoverLetter?data=${encodedData}`);
            });
        },
    });

    return (
        <GeneralBox title={"Create cover letter"} content={<CoverLetterForm formik={formik}/>}/>
    );
};

export default CoverLetter;
