export const classnamesGen = (classname, mods) => {
  const classmods = Object.keys(mods)
    .map(mod => {
      const value = mods[mod];
      let classmod;

      if (value === false || value === undefined) {
        classmod = '';
      } else if (value === true) {
        classmod = `${classname}_${mod}`;
      } else {
        classmod = `${classname}_${mod}_${value}`;
      }

      return classmod;
    })
    .filter(str => str.length);

  return [classname, ...classmods];
};

export const theme = (styles, classname, mods = {}) => {
  return classnamesGen(classname, mods)
    .map(clsName => styles[clsName])
    .join(' ');
};

export default styles => {
  return (classname, mods) => theme(styles, classname, mods);
};
