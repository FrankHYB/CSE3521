Run Train

java edu.QSJ.LogReg.TrainLogReg trainingFeature.dat trainingLabel.dat logRegModel 785 500

Run Test

java edu.QSJ.LogReg.TestLogReg logRegModel trainingFeature.dat predLabelFile 785

Run Accuracy

java edu.QSJ.LogReg.Accuracy predLabelFile trainingLabel.dat
