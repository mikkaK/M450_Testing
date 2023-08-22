import {Button, Grid} from "@mui/material";
import GridMapping from "../molecules/GridMapping";

const formFields = [
    {
        label: 'Your Details',
        fields: [
            { label: 'Name', name: 'name' },
            { label: 'Surname', name: 'surname' },
            { label: 'Age', name: 'age', type: 'number' },
        ],
    },
    {
        label: 'Your Character',
        fields: [
            { label: 'Skills', name: 'skills' },
            { label: 'Interests', name: 'interests' },
            { label: 'Weaknesses', name: 'weaknesses' },
        ],
    },
    {
        label: 'Your Future',
        fields: [
            { label: 'Applying company', name: 'appliedCompany' },
            { label: 'Applying position', name: 'position' },
            { label: 'Education level', name: 'currentEducationLevel' },
        ],
    },
];

const CoverLetterForm = ({formik}: any) => {
    return (
        <form onSubmit={formik.handleSubmit}>
            <Grid container sx={{ backgroundColor: 'white', borderRadius: '3%', padding: 5 }}>
                <GridMapping formFields={formFields} formik={formik} />
                <Grid item xs={12} sx={{ display: 'flex', justifyContent: 'flex-end' }}>
                    <Button type="submit" variant="contained" size="large">
                        Submit
                    </Button>
                </Grid>
            </Grid>
        </form>
    );
};

export default CoverLetterForm;
